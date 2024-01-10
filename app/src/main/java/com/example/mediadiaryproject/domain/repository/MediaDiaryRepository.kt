package com.example.mediadiaryproject.domain.repository

import android.graphics.Bitmap
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.models.MediaFileModel
import com.example.mediadiaryproject.domain.models.TextNoteModel
import java.io.File

interface MediaDiaryRepository {

    suspend fun savePhotoToGallery(capturePhotoBitmap: Bitmap):  Result<Unit>

    fun provideFileToSaveMedia(mediaType: MediaType): File

    fun getListOfMedia(mediaType: MediaType): List<MediaFileModel>

    suspend fun saveTextNote(textNote: TextNoteModel)

    fun getTextNotesWithDate(date: String): List<TextNoteModel>

    fun deleteTextNote(textNote: TextNoteModel)

    fun updateTextNote(textNote: TextNoteModel)

}