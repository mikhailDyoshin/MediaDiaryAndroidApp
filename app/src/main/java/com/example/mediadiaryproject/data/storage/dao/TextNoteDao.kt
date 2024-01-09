package com.example.mediadiaryproject.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mediadiaryproject.data.storage.model.TextNoteStorageModel

@Dao
interface TextNoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(textNote: TextNoteStorageModel)

    @Query("SELECT * FROM textNote ORDER BY id ASC")
    fun getUser(): List<TextNoteStorageModel>
}