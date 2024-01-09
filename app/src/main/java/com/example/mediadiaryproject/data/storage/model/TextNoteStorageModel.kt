package com.example.mediadiaryproject.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "textNote")
data class TextNoteStorageModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date: String,
    val title: String,
    val text: String
)
