package com.example.mediadiaryproject.presentation.videoslistscreen.state

import android.graphics.Bitmap
import androidx.media3.common.MediaItem

data class VideoFileState(
    val videoId: Int,
    val title: String,
    val description: String,
    val preview: Bitmap?
)
