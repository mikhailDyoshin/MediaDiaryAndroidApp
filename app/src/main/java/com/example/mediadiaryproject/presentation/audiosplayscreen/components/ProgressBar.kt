package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.ui.theme.ProgressBarActiveTrackColor
import com.example.mediadiaryproject.ui.theme.ProgressBarBackgroundColor
import com.example.mediadiaryproject.ui.theme.ProgressBarInactiveTrackColor

@Composable
fun ProgressBar(
    currentPosition: Float,
    seekTo: (position: Float) -> Unit,
    modifier: Modifier = Modifier
) {

    val sliderColors = SliderDefaults.colors(
        thumbColor = ProgressBarActiveTrackColor,
        activeTrackColor = ProgressBarActiveTrackColor,
        inactiveTrackColor = ProgressBarInactiveTrackColor
    )

    val cornerSize = 12.dp

    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = ProgressBarBackgroundColor,
                shape = RoundedCornerShape(size = cornerSize)
            )
    ) {
        Slider(
            value = currentPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { seekTo(sliderPosition) },
            colors = sliderColors,
        )
//        Text(text = currentPosition.toString())
    }
}

@Preview
@Composable
fun ProgressBarPreview() {
    ProgressBar(
        currentPosition = 0.5f,
        seekTo = {},
        modifier = Modifier.background(color = Color.White)
    )
}