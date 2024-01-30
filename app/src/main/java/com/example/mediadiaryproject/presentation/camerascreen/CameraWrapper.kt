package com.example.mediadiaryproject.presentation.camerascreen

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.camerascreen.viewmodel.CameraViewModel
import com.example.mediadiaryproject.presentation.destinations.PhotosScreenDestination
import com.example.mediadiaryproject.presentation.destinations.VideoPlayerScreenDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Destination
@Composable
fun CameraWrapper(
    navigator: DestinationsNavigator,
    viewModel: CameraViewModel = hiltViewModel(),
    dayId: Int,
) {

    val scope = rememberCoroutineScope()

    val cameraState = viewModel.state.value
    val cameraController = viewModel.cameraController.value

    val cameraPermissionState: PermissionState =
        rememberPermissionState(Manifest.permission.CAMERA)

    val audioRecordingPermissionState: PermissionState =
        rememberPermissionState(Manifest.permission.RECORD_AUDIO)

    val hasCameraPermission = cameraPermissionState.status.isGranted
    val hasAudioRecordPermission = audioRecordingPermissionState.status.isGranted

    if (hasCameraPermission && hasAudioRecordPermission) {
        CameraScreen(
            navigateToPhotos = { navigator.navigate(PhotosScreenDestination(dayId = dayId)) },
            navigateToVideos = { navigator.navigate(VideoPlayerScreenDestination(dayId = dayId)) },
            cameraState = cameraState,
            capturePhoto = { context -> viewModel.capturePhoto(context, dayId) },
            changeMode = { value -> viewModel.changeMode(value) },
            toggleCamera = { viewModel.toggleCamera() },
            recordVideo = { context -> scope.launch {
                val job = viewModel.updateFileToStoreVideo(dayId)
                job.join()
                viewModel.recordVideo(context)
            }  },
            cameraController = cameraController,
        )
    } else {
        NoPermissionScreen(
            hasCameraPermission = hasCameraPermission,
            onRequestCameraPermission = cameraPermissionState::launchPermissionRequest,
            onRequestAudioRecordingPermission = audioRecordingPermissionState::launchPermissionRequest
        )
    }

}

@Composable
fun NoPermissionScreen(
    hasCameraPermission: Boolean,
    onRequestCameraPermission: () -> Unit,
    onRequestAudioRecordingPermission: () -> Unit
) {

    NoPermissionContent(
        hasCameraPermission = hasCameraPermission,
        onRequestCameraPermission = onRequestCameraPermission,
        onRequestAudioRecordingPermission = onRequestAudioRecordingPermission,
    )

}

@Composable
fun NoPermissionContent(
    hasCameraPermission: Boolean,
    onRequestCameraPermission: () -> Unit,
    onRequestAudioRecordingPermission: () -> Unit,
) {

    LaunchedEffect(hasCameraPermission) {
        onRequestCameraPermission()
        onRequestAudioRecordingPermission()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please grant the permission to use camera")
    }
}