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


    MainContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        cameraState = cameraState,
        onPhotoCaptured = onPhotoCaptured,
    )

}

@Composable
fun MainContent(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    cameraState: CameraScreenState,
    onPhotoCaptured: (Bitmap) -> Unit
) {

    if (hasPermission) {
        CameraScreen(cameraState = cameraState, onPhotoCaptured = onPhotoCaptured)
    } else {
        NoPermissionScreen(onRequestPermission)
    }

}

@Composable
fun NoPermissionScreen(onRequestPermission: () -> Unit) {

    NoPermissionContent(
        onRequestPermission = onRequestPermission
    )

}

@Composable
fun NoPermissionContent(onRequestPermission: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please grant the permission to use camera")
        Button(onClick = onRequestPermission) {
            Text(text = "Grant permission")
        }
    }
}