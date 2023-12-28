package com.example.mediadiaryproject.presentation.audiorecordingscreen


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AudioRecordingScreen(
    recording: Boolean,
    recordingSaved: Boolean,
    amplitudeList: List<Double>,
    startRecording: () -> Unit,
    stopRecording: () -> Unit,
) {
    val context: Context = LocalContext.current

    if (recordingSaved) {
        Toast.makeText(
            context,
            "Audio saved",
            Toast.LENGTH_LONG
        ).show()
    }

    val rectList = listOf(100, 200, 300)

    Column {
        Text(text = "Audio recording screen")
        if (recording) {
            Text(text = "Recording...")
        } else {
            Text(text = "")
        }
        Button(onClick = {
            startRecording()
        }) {
            Text(text = "Start")
        }
        Button(onClick = {
            stopRecording()
        }) {
            Text(text = "Stop")
        }
//        Text(text = "$amplitude")
        androidx.compose.foundation.Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(color = Color.Gray)
                .padding(vertical = 10.dp),
            onDraw = {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val spaceBetween = 15
                val rectWidth = 20F
                val listSize = amplitudeList.size
                val horizontalMargin =
                    (canvasWidth - listSize * rectWidth - (listSize - 1) * spaceBetween) / 2

                drawLine(
                    color = Color.White,
                    start = Offset(x = 0F, y = canvasHeight / 2),
                    end = Offset(x = canvasWidth, y = canvasHeight / 2)
                )

                for (height in amplitudeList.withIndex()) {
                    val xOffset = horizontalMargin + height.index * (spaceBetween + rectWidth)
                    val rectHeight = (height.value.toFloat() * 0.1).toFloat()
                    val yOffset = canvasHeight / 2 - rectHeight / 2

                    drawRect(
                        color = Color.White,
                        topLeft = Offset(x = xOffset, y = yOffset),
                        size = Size(width = rectWidth, height = rectHeight)
                    )
                }

            }
        )
    }
}
