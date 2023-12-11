package com.example.mediadiaryproject.domain.repository

import android.graphics.Bitmap

interface ComposeSandboxRepository {

    suspend fun savePhotoToGallery(capturePhotoBitmap: Bitmap):  Result<Unit>

}