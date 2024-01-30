package com.example.mediadiaryproject.presentation.camerascreen.state

import android.graphics.Bitmap

data class CameraScreenState(
    val capturedImage: Bitmap? = null,
    val videoMode: Boolean = false,
    val recording: Boolean = false
)