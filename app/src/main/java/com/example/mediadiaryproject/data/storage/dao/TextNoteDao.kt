package com.example.mediadiaryproject.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mediadiaryproject.data.storage.model.TextNoteStorageModel

@Dao
interface TextNoteDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(textNote: TextNoteStorageModel)

    @Query("SELECT * FROM textNote WHERE dayId = :dayId")
    fun getTextNotesByDay(dayId: Int): List<TextNoteStorageModel>

    @Query("SELECT * FROM textNote WHERE id = :id")
    fun getTextNoteWithId(id: Int): TextNoteStorageModel

    @Delete
    fun delete(note: TextNoteStorageModel)

    @Update
    fun update(note: TextNoteStorageModel)
}
