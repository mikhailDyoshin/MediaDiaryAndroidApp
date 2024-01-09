package com.example.mediadiaryproject.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mediadiaryproject.data.storage.dao.TextNoteDao
import com.example.mediadiaryproject.data.storage.model.TextNoteStorageModel

@Database(entities = [TextNoteStorageModel::class], version = 1)
abstract class MediaDiaryDatabase : RoomDatabase() {
    abstract fun textNoteDao(): TextNoteDao
}