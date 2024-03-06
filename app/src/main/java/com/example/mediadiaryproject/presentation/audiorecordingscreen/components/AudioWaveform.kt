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
import com.example.mediadiaryproject.common.Constants.NUMBER_OF_COLUMNS_IN_WAVEFORM

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
//            val rectWidth = 15F
            val horizontalPadding = 10
            val verticalPadding = 80
            val minimalAmplitude = 5f
            val amplitudeGain = 0.1

            val numberOfRectangles = NUMBER_OF_COLUMNS_IN_WAVEFORM
//                (((canvasWidth - 2 * horizontalPadding) + spaceBetween) / (rectWidth + spaceBetween)).toInt()

            val rectWidth =
                (canvasWidth - (numberOfRectangles - 1) * spaceBetween - 2 * horizontalPadding) / numberOfRectangles

            val listOfAmplitudes = createListOfAmplitudes(
                initialList = amplitudes,
                numberOfRectangles = numberOfRectangles
            )

//            drawLine(
//                color = Color.White,
//                start = Offset(x = 0F, y = canvasHeight / 2),
//                end = Offset(x = canvasWidth, y = canvasHeight / 2)
//            )

            for (amp in listOfAmplitudes.withIndex()) {
                val xOffset =
                    horizontalPadding + amp.index * (spaceBetween + rectWidth)
                var rectHeight = (amp.value * amplitudeGain).toFloat()
                if (rectHeight > canvasHeight) {
                    rectHeight = canvasHeight - verticalPadding
                }
                if (rectHeight < minimalAmplitude) {
                    rectHeight = minimalAmplitude
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

private fun createListOfAmplitudes(initialList: List<Int>, numberOfRectangles: Int): List<Int> {
    val size = initialList.size

    return if (size >= numberOfRectangles) {
        initialList.subList(0, numberOfRectangles)
    } else {
        val resultList = MutableList(numberOfRectangles) { 5 }
        val startIndex = numberOfRectangles - size
        resultList.subList(startIndex, numberOfRectangles).clear()
        resultList.addAll(initialList)
        resultList
    }

}

@Preview
@Composable
fun AudioWaveformPreview() {

    AudioWaveform(amplitudes = listOf(100, 300, 200, 300, 5000, 200, 900000))
}

@Preview
@Composable
fun AudioWaveformTestPreview() {

    val testAmplitudes = List(NUMBER_OF_COLUMNS_IN_WAVEFORM) { 5000 }

    AudioWaveform(amplitudes = testAmplitudes)
}


