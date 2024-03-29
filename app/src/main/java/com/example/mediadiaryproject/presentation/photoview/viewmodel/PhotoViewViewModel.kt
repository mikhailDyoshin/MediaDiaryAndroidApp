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
import com.example.mediadiaryproject.domain.models.MediaModel
import com.example.mediadiaryproject.domain.usecase.GetMediaByIdUseCase
import com.example.mediadiaryproject.domain.usecase.UpdateMediaDataUseCase
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
    private val getPhotoUseCase: GetMediaByIdUseCase,
    private val updateMediaData: UpdateMediaDataUseCase,
) : ViewModel() {

    private val _state: MutableState<PhotoViewState?> = mutableStateOf(null)
    val state = _state

    private val _menuState: MutableState<Boolean> = mutableStateOf(true)
    val menuState = _menuState

    private val _editModeState: MutableState<Boolean> = mutableStateOf(false)
    val editModeState = _editModeState

    private val _warningWindowState: MutableState<Boolean> = mutableStateOf(false)
    val warningWindowState = _warningWindowState

    fun getPhoto(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val photoFileModel = getPhotoUseCase.execute(mediaId = photoId)
            _state.value = PhotoViewState(
                id = photoFileModel.id,
                dayId = photoFileModel.dayId,
                mediaType = photoFileModel.mediaType,
                date = photoFileModel.date,
                time = photoFileModel.time,
                title = photoFileModel.title,
                description = photoFileModel.description,
                pathToFile = photoFileModel.pathToFile,
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
        viewModelScope.launch(Dispatchers.IO) {
            _editModeState.value = false
            val updatedValue = _state.value

            if (updatedValue != null) {
                val newMediaData = MediaModel(
                    id = updatedValue.id,
                    dayId = updatedValue.dayId,
                    mediaType = updatedValue.mediaType,
                    date = updatedValue.date,
                    time = updatedValue.time,
                    title = updatedValue.title,
                    description = updatedValue.description,
                    pathToFile = updatedValue.pathToFile
                )
                updateMediaData.execute(mediaData = newMediaData)
                Log.d(
                    "Save info log",
                    "Title: ${newMediaData.title};\nDescription: ${newMediaData.description}"
                )
            } else {
                Log.d(
                    "Save info log",
                    "Data is null"
                )
            }


        }
    }

    fun displayWarningWindow() {
        _warningWindowState.value = true
    }

    fun closeWarningWindow() {
        _warningWindowState.value = false
    }

    fun updateTitle(title: String) {
        val newState = _state.value?.copy(title = title)
        _state.value = newState
    }

    fun updateDescription(description: String) {
        val newState = _state.value?.copy(description = description)
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
