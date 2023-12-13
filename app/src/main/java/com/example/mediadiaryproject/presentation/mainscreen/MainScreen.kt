package com.example.mediadiaryproject.presentation.mainscreen

import android.Manifest
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mediadiaryproject.presentation.camerascreen.CameraScreen
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    cameraState: CameraScreenState,
    onPhotoCaptured: (Bitmap) -> Unit
) {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(Manifest.permission.CAMERA)

    val audioRecordingPermissionState: PermissionState =
        rememberPermissionState(Manifest.permission.RECORD_AUDIO)


    MainContent(
        hasCameraPermission = cameraPermissionState.status.isGranted,
        hasAudioRecordPermission = audioRecordingPermissionState.status.isGranted,
        onRequestCameraPermission = cameraPermissionState::launchPermissionRequest,
        onRequestAudioRecordingPermission = audioRecordingPermissionState::launchPermissionRequest,
        cameraState = cameraState,
        onPhotoCaptured = onPhotoCaptured,
    )

}

@Composable
fun MainContent(
    hasCameraPermission: Boolean,
    hasAudioRecordPermission: Boolean,
    onRequestCameraPermission: () -> Unit,
    onRequestAudioRecordingPermission: () -> Unit,
    cameraState: CameraScreenState,
    onPhotoCaptured: (Bitmap) -> Unit
) {

    if (hasCameraPermission && hasAudioRecordPermission) {
        CameraScreen(cameraState = cameraState, onPhotoCaptured = onPhotoCaptured)
    } else {
        NoPermissionScreen(onRequestCameraPermission, onRequestAudioRecordingPermission)
    }

}

@Composable
fun NoPermissionScreen(
    onRequestCameraPermission: () -> Unit,
    onRequestAudioRecordingPermission: () -> Unit
) {

    NoPermissionContent(
        onRequestCameraPermission = onRequestCameraPermission,
        onRequestAudioRecordingPermission = onRequestAudioRecordingPermission,
    )

}

@Composable
fun NoPermissionContent(
    onRequestCameraPermission: () -> Unit,
    onRequestAudioRecordingPermission: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please grant the permission to use camera")
        Button(onClick = {
            onRequestCameraPermission()
            onRequestAudioRecordingPermission()
        }) {
            Text(text = "Grant permission")
        }
    }
}