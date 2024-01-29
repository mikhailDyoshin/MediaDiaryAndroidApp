package com.example.mediadiaryproject.presentation.camerascreen.components


import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CameraBottomBar(
    videoMode: Boolean,
    lastCapturedPhoto: Bitmap? = null,
    capturePhoto: () -> Unit,
    recordVideo: () -> Unit,
    toggleCamera: () -> Unit,
    displayLastPhoto: () -> Unit,
) {

    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { capturePhoto() }) {
                Text("Photo")
            }
            Button(onClick = { recordVideo() }) {
                Text("Video")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
                LastPhotoPreview(
                    lastCapturedPhoto = lastCapturedPhoto,
                    displayPhoto = { displayLastPhoto() }
                )
            Button(onClick = { if (videoMode) recordVideo() else capturePhoto() }) {
                Text(text = "Capture")
            }
            Button(onClick = { toggleCamera() }) {
                Text(text = "Toggle camera")
            }
        }
    }

}

@Preview
@Composable
fun CameraBottomBarPreview() {

    CameraBottomBar(
        videoMode = true,
        capturePhoto = {},
        recordVideo = {},
        toggleCamera = {},
        displayLastPhoto = {})
}
