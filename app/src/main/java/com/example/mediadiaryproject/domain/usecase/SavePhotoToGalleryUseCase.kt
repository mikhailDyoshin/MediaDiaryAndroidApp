package com.example.mediadiaryproject.domain.usecase

import android.graphics.Bitmap
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class SavePhotoToGalleryUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {

    suspend fun call(capturePhotoBitmap: Bitmap): Result<Unit> =
        repository.savePhotoToGallery(capturePhotoBitmap = capturePhotoBitmap)
}