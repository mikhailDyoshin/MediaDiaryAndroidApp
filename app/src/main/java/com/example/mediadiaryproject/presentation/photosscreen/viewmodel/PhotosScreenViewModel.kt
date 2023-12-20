package com.example.mediadiaryproject.presentation.photosscreen.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.GetListOfMediaUseCase
import com.example.mediadiaryproject.presentation.photosscreen.state.PhotoState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
@SuppressLint("StaticFieldLeak")
@HiltViewModel
class PhotosScreenViewModel @Inject constructor(
     @ApplicationContext private val context: Context,
    private val getListOfAllPhotos: GetListOfMediaUseCase,
) : ViewModel() {


    private val _state: MutableState<List<PhotoState>> = mutableStateOf(listOf())
    val state = _state

    init {
        getPhotosList()
    }

    private fun getPhotosList() {
        _state.value = getListOfAllPhotos.execute(mediaType = MediaType.PHOTO).map { videoFileModel ->
            PhotoState(
                fileName = videoFileModel.fileName,
                image = loadImageFromInternalStorage(imageFileName = videoFileModel.fileName)
                )
        }
    }

    private fun loadImageFromInternalStorage(imageFileName: String): Bitmap? {

        val directory = MediaType.PHOTO.directory

        val file = File(context.getExternalFilesDir(directory), imageFileName)

        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }

}