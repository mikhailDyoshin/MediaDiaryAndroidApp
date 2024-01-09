package com.example.mediadiaryproject.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "textNote")
data class TextNoteStorageModel(val title: String, val text: String) {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
