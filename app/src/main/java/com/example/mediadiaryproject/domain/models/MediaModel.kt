package com.example.mediadiaryproject.domain.models

import com.example.mediadiaryproject.common.MediaType

data class MediaModel(
    var id: Int = 0,
    var dayId: Int,
    val mediaType: MediaType,
    var date: String,
    var time: String,
    val title: String,
    val description: String,
    val pathToFile: String,
)
