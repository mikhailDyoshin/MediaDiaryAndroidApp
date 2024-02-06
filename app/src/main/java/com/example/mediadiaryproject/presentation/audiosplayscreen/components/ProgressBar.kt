package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ProgressBar(currentPosition: Float, seekTo: (position: Float) -> Unit) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        androidx.compose.material3.Slider(
            value = currentPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { seekTo(sliderPosition) }
        )
        Text(text = currentPosition.toString())
    }
}