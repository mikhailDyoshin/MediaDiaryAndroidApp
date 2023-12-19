package com.example.mediadiaryproject.presentation.camerascreen

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.camerascreen.components.CameraContent
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState

@Composable
fun CameraScreen(
    cameraState: CameraScreenState,
    capturePhoto: (context: Context) -> Unit,
    navigateToVideos: () -> Unit,
    toggleCamera: () -> Unit,
    recordVideo: (context: Context) -> Unit,
    cameraController: LifecycleCameraController,
    ) {

    CameraContent(
        navigateToVideos = { navigateToVideos() },
        capturePhoto = { context -> capturePhoto(context) },
        lastCapturedPhoto = cameraState.capturedImage,
        toggleCamera = { toggleCamera() },
        recordVideo = { context -> recordVideo(context) },
        cameraController = cameraController,
    )
}