package com.example.mediadiaryproject.presentation.camerascreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.ui.theme.HalfTransparent

@Composable
fun CaptureButton(capture: () -> Unit, recording: Boolean, videoMode: Boolean) {
    Canvas(
        modifier = Modifier
            .size(65.dp)
            .background(color = HalfTransparent, shape = RoundedCornerShape(65.dp))
            .clickable { capture() }
    ) {
        val stroke = 20f
        val outerCircleRadius = size.minDimension / 2f - stroke / 2
        val innerCircleRadius = outerCircleRadius * 0.5f

        val outerCircleCenter = center
        val innerCircleCenter = center

        val innerCircleColor = if (videoMode) Color.Red else Color.White

        val stopButtonCornerRadius = 5.dp

        // Draw outer circle
        drawCircle(
            color = Color.White,
            radius = outerCircleRadius,
            center = outerCircleCenter,
            style = Stroke(20f) // Set the stroke width for the outer circle
        )



        if (recording) {
            // Draw stop button
            drawRoundRect(
                color = Color.Black,
                topLeft = center - Offset(size.width / 4, size.height / 4),
                size = size / 2f,
                cornerRadius = CornerRadius(
                    stopButtonCornerRadius.toPx(),
                    stopButtonCornerRadius.toPx()
                )
            )
        } else {
            // Draw inner circle
            drawCircle(
                color = innerCircleColor,
                radius = innerCircleRadius,
                center = innerCircleCenter
            )
        }

    }
}

@Preview
@Composable
fun CaptureButtonPhotoModePreview() {
    CaptureButton(capture = {}, recording = false, videoMode = false)
}

@Preview
@Composable
fun CaptureButtonVideoModePreview() {
    CaptureButton(capture = {}, recording = false, videoMode = true)
}

@Preview
@Composable
fun CaptureButtonRecordingPreview() {
    CaptureButton(capture = {}, recording = true, videoMode = true)
}
