package com.example.mediadiaryproject.presentation.camerascreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CaptureButton(capture: () -> Unit) {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .background(color = Color(1f, 1f, 1f, 0.2f), shape = RoundedCornerShape(65.dp))
            .clickable { capture() }
    ) {
        val stroke = 20f
        val outerCircleRadius = size.minDimension / 2f - stroke / 2
        val innerCircleRadius = outerCircleRadius * 0.5f

        val outerCircleCenter = center
        val innerCircleCenter = center

        // Draw outer circle
        drawCircle(
            color = Color.White,
            radius = outerCircleRadius,
            center = outerCircleCenter,
            style = Stroke(20f) // Set the stroke width for the outer circle
        )

        // Draw inner circle
        drawCircle(
            color = Color.White,
            radius = innerCircleRadius,
            center = innerCircleCenter
        )
    }
}

@Preview
@Composable
fun CaptureButtonPreview() {
    CaptureButton(capture = {})
}
