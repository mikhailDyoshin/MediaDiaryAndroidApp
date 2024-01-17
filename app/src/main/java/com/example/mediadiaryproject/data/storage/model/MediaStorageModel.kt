package com.example.mediadiaryproject.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mediadiaryproject.common.MediaType

@Entity(tableName = "media")
data class MediaStorageModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var dayId: Int,
    val mediaType: MediaType,
    var date: String,
    var time: String,
    val title: String,
    val description: String
)
