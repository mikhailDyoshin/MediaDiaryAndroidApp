package com.example.mediadiaryproject.data

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.data.storage.dao.DayDao
import com.example.mediadiaryproject.data.storage.dao.MediaDao
import com.example.mediadiaryproject.data.storage.dao.TextNoteDao
import com.example.mediadiaryproject.data.storage.model.DayStorageModel
import com.example.mediadiaryproject.data.storage.model.MediaStorageModel
import com.example.mediadiaryproject.data.storage.model.TextNoteStorageModel
import com.example.mediadiaryproject.domain.models.CollectionModel
import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.models.MediaModel
import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.OutputStream
import javax.inject.Inject

class MediaDiaryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val textNoteDao: TextNoteDao,
    private val dayDao: DayDao,
    private val mediaDao: MediaDao,
) : MediaDiaryRepository {
    override suspend fun savePhotoToGallery(capturePhotoBitmap: Bitmap): Result<Unit> =
        withContext(Dispatchers.IO) {

            val resolver: ContentResolver = context.applicationContext.contentResolver

            // Find all image files on the primary external storage device.
            val imageCollection: Uri = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL_PRIMARY
                )

                else -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            // Publish a new image.
            val nowTimestamp: Long = System.currentTimeMillis()
            val imageContentValues: ContentValues = ContentValues().apply {

                put(MediaStore.Images.Media.DISPLAY_NAME, "Your image name" + ".jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.DATE_TAKEN, nowTimestamp)
                    put(
                        MediaStore.MediaColumns.RELATIVE_PATH,
                        Environment.DIRECTORY_DCIM + "/YourAppNameOrAnyOtherSubFolderName"
                    )
                    put(MediaStore.MediaColumns.IS_PENDING, 1)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    put(MediaStore.Images.Media.DATE_TAKEN, nowTimestamp)
                    put(MediaStore.Images.Media.DATE_ADDED, nowTimestamp)
                    put(MediaStore.Images.Media.DATE_MODIFIED, nowTimestamp)
                    put(MediaStore.Images.Media.AUTHOR, "Your Name")
                    put(MediaStore.Images.Media.DESCRIPTION, "Your description")
                }
            }

            val imageMediaStoreUri: Uri? = resolver.insert(imageCollection, imageContentValues)

            // Write the image data to the new Uri in case you need to modify it later.
            val result: Result<Unit> = imageMediaStoreUri?.let { uri ->
                kotlin.runCatching {
                    resolver.openOutputStream(uri).use { outputStream: OutputStream? ->
                        checkNotNull(outputStream) {
                            "Couldn't create file for gallery, MediaStore output stream is null"
                        }
                        capturePhotoBitmap.compress(
                            Bitmap.CompressFormat.JPEG,
                            100,
                            outputStream
                        )
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        imageContentValues.clear()
                        imageContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                        resolver.update(uri, imageContentValues, null, null)
                    }

                    Result.success(Unit)
                }.getOrElse { exception: Throwable ->
                    exception.message?.let(::println)
                    resolver.delete(uri, null, null)
                    Result.failure(exception)
                }
            } ?: run {
                Result.failure(Exception("Couldn't create file for gallery"))
            }

            return@withContext result
        }

    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun provideFileToSaveMedia(media: MediaModel): File {
        // save a media's info to the database
        val createdMediaId = mediaDao.insert(
            media = MediaStorageModel(
                dayId = media.dayId,
                mediaType = media.mediaType,
                date = media.date,
                time = media.time,
                title = media.title,
                description = media.description,
            )
        ).toInt()

        // provide a file where to save a media
        return provideFileForMedia(mediaType = media.mediaType, mediaId = createdMediaId)

    }

    override fun getMediaByDayAndType(dayId: Int, mediaType: MediaType): List<MediaModel> {
        val listOfMediaLinks = mediaDao.getMediaByDayAndType(dayId = dayId, type = mediaType)

        return listOfMediaLinks.map { mediaLink ->
            MediaModel(
                id = mediaLink.id,
                dayId = mediaLink.id,
                mediaType = mediaLink.mediaType,
                date = mediaLink.date,
                time = mediaLink.time,
                title = mediaLink.title,
                description = mediaLink.description,
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun provideFileForMedia(mediaType: MediaType, mediaId: Int): File {

        val directory = mediaType.directory

        return File(
            context.getExternalFilesDir(directory),
            "$mediaId"
        )

    }

//    private fun getListOfMedia(mediaType: MediaType): List<MediaFileModel> {
//
//        val type = mediaType.type
//
//        val directoryName = mediaType.directory
//
//        val directory = File(context.getExternalFilesDir(directoryName).toString())
//
//        val files: Array<out File>? = directory.listFiles()
//
//        if (files != null) {
//
//            if (files.isEmpty()) {
//                Log.d("Recorded media file", "No files in the directory.")
//                return emptyList()
//            }
//
//            return files.filter { file -> file.name.startsWith(prefix = type) }
//                .map { file -> MediaFileModel(fileName = file.name, filePath = file.path) }
//
//        } else {
//            // The directory is empty or doesn't exist
//            Log.d("Recorded media file", "The directory is empty or doesn't exist.")
//            return emptyList()
//        }
//    }

    override suspend fun saveTextNote(textNote: TextNoteModel) {
        val textNoteForStorage =
            TextNoteStorageModel(
                dayId = textNote.dayId,
                date = textNote.date,
                title = textNote.title,
                text = textNote.text
            )
        textNoteDao.insert(textNote = textNoteForStorage)
    }

    override fun getTextNotesByDay(dayId: Int): List<TextNoteModel> {
        return textNoteDao.getTextNotesByDay(dayId = dayId).map { textNote ->
            TextNoteModel(
                id = textNote.id,
                dayId = textNote.dayId,
                date = textNote.date,
                title = textNote.title,
                text = textNote.text
            )
        }
    }

    override fun getTextNoteById(id: Int): TextNoteModel {
        val textNoteFromStorage = textNoteDao.getTextNoteWithId(id = id)
        return TextNoteModel(
            id = textNoteFromStorage.id,
            dayId = textNoteFromStorage.dayId,
            date = textNoteFromStorage.date,
            title = textNoteFromStorage.title,
            text = textNoteFromStorage.text
        )
    }

    override fun deleteTextNote(textNote: TextNoteModel) {
        val textNoteForStorage =
            TextNoteStorageModel(
                id = textNote.id,
                dayId = textNote.dayId,
                date = textNote.date,
                title = textNote.title,
                text = textNote.text
            )
        textNoteDao.delete(note = textNoteForStorage)
    }

    override fun updateTextNote(textNote: TextNoteModel) {
        val textNoteForStorage =
            TextNoteStorageModel(
                id = textNote.id,
                dayId = textNote.dayId,
                date = textNote.date,
                title = textNote.title,
                text = textNote.text
            )
        textNoteDao.update(note = textNoteForStorage)
    }

    override suspend fun saveDay(day: DayModel, collection: CollectionModel) {
        val dayToStore =
            DayStorageModel(date = day.date, collectionId = collection.id)
        dayDao.insert(day = dayToStore)
    }

    override suspend fun getDayByCollectionAndDate(
        date: String,
        collection: CollectionModel
    ): DayModel {
        val day = dayDao.getDayByCollectionAndDate(collectionId = collection.id, date = date)

        if (day != null) {
            return DayModel(id = day.id, date = day.date)
        }

        val createdDayId =
            dayDao.insert(day = DayStorageModel(date = date, collectionId = collection.id)).toInt()

        val createdDay = dayDao.getDayById(id = createdDayId)

        return DayModel(id = createdDay.id, date = createdDay.date)
    }

    override fun getDaysByCollection(collectionId: Int): List<DayModel> {
        return dayDao.getDaysByCollection(collectionId = collectionId).map { day ->
            DayModel(
                id = day.id,
                date = day.date,
            )
        }
    }

    override fun getDayById(dayId: Int): DayModel {
        val dayFromStorage = dayDao.getDayById(id = dayId)
        return DayModel(
            id = dayFromStorage.id,
            date = dayFromStorage.date,
        )
    }

    override fun deleteDay(day: DayModel, collection: CollectionModel) {
        val dayToDelete =
            DayStorageModel(
                id = day.id,
                date = day.date,
                collectionId = collection.id
            )
        dayDao.delete(day = dayToDelete)
    }
}
