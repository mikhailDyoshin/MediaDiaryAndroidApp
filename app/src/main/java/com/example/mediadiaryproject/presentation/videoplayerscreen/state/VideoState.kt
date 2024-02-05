package com.example.mediadiaryproject.presentation.videoplayerscreen.state

import androidx.media3.common.MediaItem

data class VideoState(
    val id: Int,
    val description: String,
    val title: String,
    val mediaItem: MediaItem,
    val date: String,
    val time: String
)
