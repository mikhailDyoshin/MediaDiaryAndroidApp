package com.example.mediadiaryproject.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.data.storage.model.MediaStorageModel
import com.example.mediadiaryproject.data.storage.model.TextNoteStorageModel

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(media: MediaStorageModel): Long

    @Query("SELECT * FROM media WHERE id = :id")
    fun getMediaById(id: Int): MediaStorageModel

    @Query("SELECT * FROM media WHERE dayId = :dayId and mediaType = :type")
    fun getMediaByDayAndType(dayId: Int, type: MediaType): List<MediaStorageModel>

    @Update
    fun update(media: MediaStorageModel)

}