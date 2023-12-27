package com.example.mediadiaryproject.presentation.audiorecordingscreen


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    amplitudeList: List<Int>,
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
            modifier = Modifier.fillMaxWidth().height(300.dp).background(color = Color.Gray),
            onDraw = {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val spaceBetween = 5

                for (height in amplitudeList.withIndex()) {
                    val xOffset = spaceBetween + height.index * (10 + spaceBetween)
                    val rectHeight = (height.value.toFloat() * 0.1).toFloat()
                    val yOffset = canvasHeight/2 - rectHeight/2

                    drawRect(
                        color = Color.White,
                        topLeft = Offset(x = xOffset.toFloat(), y = yOffset),
                        size = Size(width = 20F, height = rectHeight)
                    )
                }

            }
        )
    }
}
