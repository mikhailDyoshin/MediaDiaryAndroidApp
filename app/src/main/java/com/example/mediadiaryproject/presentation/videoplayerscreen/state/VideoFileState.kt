package com.example.mediadiaryproject.presentation.videoplayerscreen.state

import android.graphics.Bitmap
import androidx.media3.common.MediaItem

data class VideoFileState(val videoId: Int, val fileName: String, val preview: Bitmap?)
