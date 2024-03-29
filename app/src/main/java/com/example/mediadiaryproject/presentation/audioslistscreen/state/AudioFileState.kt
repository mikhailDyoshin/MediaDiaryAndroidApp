package com.example.mediadiaryproject.presentation.audioslistscreen.state

import androidx.media3.common.MediaItem

data class AudioFileState(
    val fileName: String,
    val mediaItem: MediaItem?,
    val underFocus: Boolean = false,
    val isPlaying: Boolean = false,
)
