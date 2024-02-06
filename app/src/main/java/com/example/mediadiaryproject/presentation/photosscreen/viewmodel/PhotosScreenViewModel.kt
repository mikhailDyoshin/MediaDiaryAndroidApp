package com.example.mediadiaryproject.presentation.photosscreen.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.usecase.GetListOfMediaByDayAndTypeUseCase
import com.example.mediadiaryproject.presentation.photosscreen.state.PhotoState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class PhotosScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getListOfAllPhotos: GetListOfMediaByDayAndTypeUseCase,
) : ViewModel() {


    private val _state: MutableState<List<PhotoState>> = mutableStateOf(listOf())
    val state = _state

//    init {
//        getPhotosList()
//    }

    fun getPhotosList(dayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = getListOfAllPhotos.execute(mediaType = MediaType.PHOTO, dayId = dayId)
                .map { photoFileModel ->
                    PhotoState(
                        title = photoFileModel.title,
                        description = photoFileModel.description,
                        image = loadImageFromInternalStorage(
                            pathToFile = photoFileModel.id.toString(),
                            mediaType = MediaType.PHOTO
                        )
                    )
                }
        }

    }

    private fun loadImageFromInternalStorage(pathToFile: String, mediaType: MediaType): Bitmap? {

        val directory = mediaType.directory

        val file = File(context.getExternalFilesDir(directory), pathToFile)

        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }

}