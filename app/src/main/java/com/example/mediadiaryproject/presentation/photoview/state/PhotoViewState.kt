package com.example.mediadiaryproject.presentation.photoview.state

import android.graphics.Bitmap
import com.example.mediadiaryproject.common.MediaType

data class PhotoViewState(
    var id: Int = 0,
    var dayId: Int,
    val mediaType: MediaType,
    var date: String,
    var time: String,
    val title: String,
    val description: String,
    val pathToFile: String,
    val image: Bitmap?
)
