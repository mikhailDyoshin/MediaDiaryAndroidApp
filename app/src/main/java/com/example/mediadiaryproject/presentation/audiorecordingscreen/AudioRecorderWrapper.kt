package com.example.mediadiaryproject.presentation.audiorecordingscreen

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPermissionsApi::class)
@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun AudioRecorderWrapper (navigator: DestinationsNavigator) {

    val audioRecordingPermissionState: PermissionState =
        rememberPermissionState(Manifest.permission.RECORD_AUDIO)

    val hasAudioRecordPermission = audioRecordingPermissionState.status.isGranted

    if (hasAudioRecordPermission) {
        AudioRecordingScreen()
    } else {
        NoRecordingAudioPermissionScreen(
            onRequestAudioRecordingPermission = audioRecordingPermissionState::launchPermissionRequest
        )
    }
}

@Composable
fun NoRecordingAudioPermissionScreen(
    onRequestAudioRecordingPermission: () -> Unit
) {

    NoRecordingAudioPermissionContent(
        onRequestAudioRecordingPermission = onRequestAudioRecordingPermission,
    )

}

@Composable
fun NoRecordingAudioPermissionContent(
    onRequestAudioRecordingPermission: () -> Unit,
) {

    LaunchedEffect(true) {
        onRequestAudioRecordingPermission()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please grant the permission to use audio recorder")
    }
}