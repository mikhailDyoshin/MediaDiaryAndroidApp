package com.example.mediadiaryproject.domain.repository

import android.graphics.Bitmap
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.models.CollectionModel
import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.models.MediaFileModel
import com.example.mediadiaryproject.domain.models.TextNoteModel
import java.io.File

interface MediaDiaryRepository {

    suspend fun savePhotoToGallery(capturePhotoBitmap: Bitmap):  Result<Unit>

    fun provideFileToSaveMedia(mediaType: MediaType): File

    fun getListOfMedia(mediaType: MediaType): List<MediaFileModel>

    suspend fun saveTextNote(textNote: TextNoteModel)

    fun getTextNotesWithDate(date: String): List<TextNoteModel>

    fun getTextNoteById(id: Int): TextNoteModel

    fun deleteTextNote(textNote: TextNoteModel)

    fun updateTextNote(textNote: TextNoteModel)

    suspend fun saveDay(day: DayModel, collection: CollectionModel)

    suspend fun getDayByCollectionAndDate(date: String, collection: CollectionModel): DayModel

    fun getDaysByCollection(collectionId: Int): List<DayModel>

    fun getDayById(dayId: Int): DayModel

    fun deleteDay(day: DayModel, collection: CollectionModel)

//    fun updateDay(day: DayModel)

}