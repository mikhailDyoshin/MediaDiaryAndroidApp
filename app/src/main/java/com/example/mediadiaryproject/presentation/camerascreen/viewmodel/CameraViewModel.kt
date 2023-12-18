package com.example.mediadiaryproject.presentation.camerascreen.viewmodel

import android.content.Context
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState
import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.domain.SavePhotoToGalleryUseCase
import com.example.mediadiaryproject.domain.ProvideFileToSaveVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltViewModel
class CameraViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val savePhotoToGalleryUseCase: SavePhotoToGalleryUseCase,
    private val provideFileToSaveVideoUseCase: ProvideFileToSaveVideoUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(CameraScreenState())
    val state = _state

    fun storePhotoInGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            savePhotoToGalleryUseCase.call(bitmap)
            updateCapturedPhotoState(bitmap)
        }
    }

    fun provideFileToSaveVideoUseCase(): File {
        return provideFileToSaveVideoUseCase.execute()
    }

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.capturedImage?.recycle()
        _state.value = _state.value.copy(capturedImage = updatedPhoto)
    }

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }

}