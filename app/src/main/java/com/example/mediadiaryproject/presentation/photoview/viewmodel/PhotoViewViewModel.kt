package com.example.mediadiaryproject.presentation.photoview.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.usecase.GetMediaByIdUseCase
import com.example.mediadiaryproject.presentation.photoview.state.PhotoViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class PhotoViewViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getPhotoUseCase: GetMediaByIdUseCase
) : ViewModel() {

    private val _state: MutableState<PhotoViewState?> = mutableStateOf(null)
    val state = _state

    private val _menuState: MutableState<Boolean> = mutableStateOf(true)
    val menuState = _menuState

    private val _editModeState: MutableState<Boolean> = mutableStateOf(false)
    val editModeState = _editModeState

    fun getPhoto(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val photoFileModel = getPhotoUseCase.execute(mediaId = photoId)
            _state.value = PhotoViewState(
                title = photoFileModel.title,
                description = photoFileModel.description,
                image = loadImageFromInternalStorage(
                    imageId = photoFileModel.id.toString(),
                    mediaType = MediaType.PHOTO
                )
            )
        }
    }

    fun toggleMenu() {
        _menuState.value = !_menuState.value
    }

    fun turnOnEditMode() {
        _editModeState.value = true
    }

    fun saveInfo() {
        _editModeState.value = false
        Log.d("Save info log", "Title: ${_state.value?.title};\nDescription: ${_state.value?.description}")
    }

    fun updateTitle(title: String) {
        val newState = _state.value?.copy(title = title)
        _state.value = newState
    }

    fun updateDescription(description: String) {
       val newState =  _state.value?.copy(description = description)
        _state.value = newState
    }

    private fun loadImageFromInternalStorage(imageId: String, mediaType: MediaType): Bitmap? {

        val directory = mediaType.directory

        val file = File(context.getExternalFilesDir(directory), imageId)

        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }

}