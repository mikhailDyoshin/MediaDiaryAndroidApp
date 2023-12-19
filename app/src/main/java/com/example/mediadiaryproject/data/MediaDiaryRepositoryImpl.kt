package com.example.mediadiaryproject.data

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.models.MediaFileModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.OutputStream
import javax.inject.Inject

class MediaDiaryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
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
    override fun provideFileToSaveMedia(mediaType: MediaType): File {
        val nowTimestamp: Long = System.currentTimeMillis()

        val type = mediaType.type

        val directory = mediaType.directory

        return File(
            context.getExternalFilesDir(directory),
            "${type}_$nowTimestamp"
        )

//        when (mediaType) {
//            MediaType.VIDEO -> {
//                return File(
//                    context.getExternalFilesDir(Environment.DIRECTORY_DCIM),
//                    "${type}_$nowTimestamp"
//                )
//            }
//            MediaType.PHOTO -> {
//                return File(
//                    context.getExternalFilesDir(Environment.DIRECTORY_DCIM),
//                    "${type}_$nowTimestamp"
//                )
//            }
//            MediaType.AUDIO -> {
//                return File(
//                    context.getExternalFilesDir(Environment.DIRECTORY_RECORDINGS),
//                    "${type}_$nowTimestamp"
//                )
//            }
//        }
    }

    override fun getListOfMedia(mediaType: MediaType): List<MediaFileModel> {

//        when (mediaType) {
//            MediaType.VIDEO -> {
//
//            }
//        }

        val type = mediaType.type

        val directoryName = mediaType.directory

        val directory = File(context.getExternalFilesDir(directoryName).toString())

        val files: Array<out File>? = directory.listFiles()

        if (files != null) {

            if (files.isEmpty()) {
                Log.d("Recorded video file", "No files in the directory.")
                return emptyList()
            }

            return files.filter { file -> file.name.startsWith(prefix = type) }
                .map { file -> MediaFileModel(fileName = file.name, filePath = file.path) }

        } else {
            // The directory is empty or doesn't exist
            Log.d("Recorded video file", "The directory is empty or doesn't exist.")
            return emptyList()
        }
    }

}