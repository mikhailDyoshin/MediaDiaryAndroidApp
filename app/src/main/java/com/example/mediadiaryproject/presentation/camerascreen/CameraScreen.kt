package com.example.mediadiaryproject.presentation.camerascreen

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.camerascreen.components.CameraContent
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState

@Composable
fun CameraScreen(
    cameraState: CameraScreenState, onPhotoCaptured: (Bitmap) -> Unit
) {

    CameraContent(
        onPhotoCaptured = { bitmap -> onPhotoCaptured(bitmap) },
        lastCapturedPhoto = cameraState.capturedImage
    )
}