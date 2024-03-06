package com.example.mediadiaryproject.presentation.audiorecordingscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartStopButton(recording: Boolean, start: () -> Unit, stop: () -> Unit) {

    val buttonSize = 81.dp

    IconButton(
        onClick = {
            if (recording) {
                stop()
            } else {
                start()
            }
        },
        modifier = Modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(size = buttonSize)
            )
            .size(buttonSize)
    ) {
        if (recording) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Stop icon")
        } else {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Start icon")
        }
    }
}

@Preview
@Composable
fun StartStopButtonRecordingPreview() {
    StartStopButton(recording = true, start = {}, stop = {})
}

@Preview
@Composable
fun StartStopButtonPreview() {
    StartStopButton(recording = false, start = {}, stop = {})
}