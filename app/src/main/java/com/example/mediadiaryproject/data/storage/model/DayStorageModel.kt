package com.example.mediadiaryproject.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class DayStorageModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date: String,
    var collectionId: Int,
)
