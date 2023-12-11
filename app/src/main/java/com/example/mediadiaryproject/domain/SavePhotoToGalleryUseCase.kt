package com.example.mediadiaryproject.domain

import android.graphics.Bitmap
import com.example.mediadiaryproject.domain.repository.ComposeSandboxRepository
import javax.inject.Inject

class SavePhotoToGalleryUseCase @Inject constructor(
    private val repository: ComposeSandboxRepository
) {

    suspend fun call(capturePhotoBitmap: Bitmap): Result<Unit> =
        repository.savePhotoToGallery(capturePhotoBitmap = capturePhotoBitmap)
}