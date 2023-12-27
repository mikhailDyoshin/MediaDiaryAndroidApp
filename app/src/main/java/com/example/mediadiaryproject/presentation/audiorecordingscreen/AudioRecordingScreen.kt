package com.example.mediadiaryproject.presentation.audiorecordingscreen


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AudioRecordingScreen(
    recording: Boolean,
    recordingSaved: Boolean,
    amplitude: Int,
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
        Text(text = "$amplitude")
    }
}
