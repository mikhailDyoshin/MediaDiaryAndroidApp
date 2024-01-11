package com.example.mediadiaryproject.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mediadiaryproject.data.storage.model.DayStorageModel

@Dao
interface DayDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(day: DayStorageModel)

    @Query("SELECT * FROM days WHERE id = :collectionId")
    fun getDaysByCollection(collectionId: Int): List<DayStorageModel>

    @Query("SELECT * FROM days WHERE id = :id")
    fun getDayById(id: Int): DayStorageModel

    @Delete
    fun delete(day: DayStorageModel)

    @Update
    fun update(day: DayStorageModel)
}