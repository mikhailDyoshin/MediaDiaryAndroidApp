package com.example.mediadiaryproject.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mediadiaryproject.data.storage.dao.DayDao
import com.example.mediadiaryproject.data.storage.dao.MediaDao
import com.example.mediadiaryproject.data.storage.dao.TextNoteDao
import com.example.mediadiaryproject.data.storage.model.DayStorageModel
import com.example.mediadiaryproject.data.storage.model.MediaStorageModel
import com.example.mediadiaryproject.data.storage.model.TextNoteStorageModel

@Database(
    entities = [TextNoteStorageModel::class, DayStorageModel::class, MediaStorageModel::class],
    version = 1
)
abstract class MediaDiaryDatabase : RoomDatabase() {
    abstract fun textNoteDao(): TextNoteDao

    abstract fun dayDao(): DayDao

    abstract fun mediaDao(): MediaDao
}