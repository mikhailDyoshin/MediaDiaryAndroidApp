package com.example.mediadiaryproject.presentation.camerascreen

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.mediadiaryproject.presentation.camerascreen.components.CameraContent
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun CameraScreen(
    navigateToPhotos: () -> Unit,
    navigateToVideos: () -> Unit,
    cameraState: CameraScreenState,
    capturePhoto: (context: Context) -> Unit,
    changeMode: (value: Boolean) -> Unit,
    toggleCamera: () -> Unit,
    recordVideo: (context: Context) -> Unit,
    cameraController: LifecycleCameraController,
    ) {

    CameraContent(
        navigateToPhotos = { navigateToPhotos() },
        navigateToVideos = { navigateToVideos() },
        capturePhoto = { context -> capturePhoto(context) },
        changeMode = { value -> changeMode(value) },
        cameraScreenState = cameraState,
        toggleCamera = { toggleCamera() },
        recordVideo = { context -> recordVideo(context) },
        cameraController = cameraController,
    )
}