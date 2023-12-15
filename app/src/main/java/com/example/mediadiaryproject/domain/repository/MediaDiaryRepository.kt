package com.example.mediadiaryproject.domain.repository

import android.graphics.Bitmap
import com.example.mediadiaryproject.domain.models.VideoFileModel
import java.io.File

interface MediaDiaryRepository {

    suspend fun savePhotoToGallery(capturePhotoBitmap: Bitmap):  Result<Unit>

    fun provideFileToSaveVideo(): File

    fun getListOfAllVideos(): List<VideoFileModel>

}