package com.example.mediadiaryproject.presentation.audiorecordingscreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AudioWaveform(amplitudes: List<Int>) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = Color.Gray),
        contentDescription = "Audio waveform",
        onDraw = {

            val canvasWidth = size.width
            val canvasHeight = size.height
            val spaceBetween = 5
            val rectWidth = 15F
            val horizontalPadding = 70
            val verticalPadding = 40

            val numberOfRectangles =
                (((canvasWidth - 2 * horizontalPadding) + spaceBetween) / (rectWidth + spaceBetween)).toInt()

            val listOfAmplitudes = createListOfAmplitudes(
                initialList = amplitudes,
                numberOfRectangles = numberOfRectangles
            )

            drawLine(
                color = Color.White,
                start = Offset(x = 0F, y = canvasHeight / 2),
                end = Offset(x = canvasWidth, y = canvasHeight / 2)
            )

            for (amp in listOfAmplitudes.withIndex()) {
                val xOffset =
                    horizontalPadding + amp.index * (spaceBetween + rectWidth) - spaceBetween
                var rectHeight = (amp.value.toFloat() * 0.1).toFloat()
                if (rectHeight > canvasHeight) {
                    rectHeight = canvasHeight - verticalPadding
                }
                val yOffset = canvasHeight / 2 - rectHeight / 2

                drawRect(
                    color = Color.White,
                    topLeft = Offset(x = xOffset, y = yOffset),
                    size = Size(width = rectWidth, height = rectHeight)
                )
            }

        })

}

@Preview
@Composable
fun AudioWaveformPreview() {
    AudioWaveform(amplitudes = listOf(1000,3000, 2000, 3000, 50000))
}

private fun createListOfAmplitudes(initialList: List<Int>, numberOfRectangles: Int): List<Int> {
    val size = initialList.size

    return if (size >= numberOfRectangles) {
        initialList.subList(0, numberOfRectangles - 1)
    } else {
        val resultList = MutableList(numberOfRectangles) { 50 }
        val startIndex = numberOfRectangles - size
        resultList.subList(startIndex, numberOfRectangles - 1).clear()
        resultList.addAll(initialList)
        resultList
    }

}
