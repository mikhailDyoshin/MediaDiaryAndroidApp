package com.example.mediadiaryproject.presentation.camerascreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CameraTopBar(
    navigateToPhotos: () -> Unit,
    navigateToVideos: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { navigateToPhotos() }) {
            Text("Photos")
        }
        Button(onClick = { navigateToVideos() }) {
            Text("Videos")
        }
    }
}

@Preview
@Composable
fun CameraTopBarPreview() {
    CameraTopBar(navigateToVideos = {}, navigateToPhotos = {})
}
