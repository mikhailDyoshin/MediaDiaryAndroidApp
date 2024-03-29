package com.example.mediadiaryproject.domain.repository

import android.graphics.Bitmap
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.models.CollectionModel
import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.models.FileModel
import com.example.mediadiaryproject.domain.models.MediaModel
import com.example.mediadiaryproject.domain.models.TextNoteModel

interface MediaDiaryRepository {

    suspend fun savePhotoToGallery(capturePhotoBitmap: Bitmap):  Result<Unit>

//    fun provideFileToSaveMedia(mediaType: MediaType): File

//    fun getListOfMedia(mediaType: MediaType): List<MediaFileModel>

    suspend fun provideFileToSaveMedia(media: MediaModel): FileModel

    suspend fun getMediaByDayAndType(dayId: Int, mediaType: MediaType): List<MediaModel>

    suspend fun saveTextNote(textNote: TextNoteModel)

    fun updateMediaData(mediaData: MediaModel)

    fun getTextNotesByDay(dayId: Int): List<TextNoteModel>

    fun getTextNoteById(id: Int): TextNoteModel

    fun deleteTextNote(textNote: TextNoteModel)

    fun updateTextNote(textNote: TextNoteModel)

    suspend fun saveDay(day: DayModel, collection: CollectionModel)

    suspend fun getDayByCollectionAndDate(date: String, collection: CollectionModel): DayModel

    fun getDaysByCollection(collectionId: Int): List<DayModel>

    fun getDayById(dayId: Int): DayModel

    fun deleteDay(day: DayModel, collection: CollectionModel)

//    fun updateDay(day: DayModel)

    suspend fun getMediaById(mediaId: Int): MediaModel

}