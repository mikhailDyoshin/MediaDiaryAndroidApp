package com.example.mediadiaryproject.presentation.audiorecordingscreen


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.audiorecordingscreen.components.AudioRecorderTopBar
import com.example.mediadiaryproject.presentation.audiorecordingscreen.components.AudioWaveform
import com.example.mediadiaryproject.presentation.audiorecordingscreen.components.StartStopButton
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AudioRecordingScreen(
    recording: Boolean,
    recordingSaved: Boolean,
    amplitudeList: List<Int>,
    startRecording: () -> Unit,
    stopRecording: () -> Unit,
    navigateToAudios: () -> Unit,
    navigateBack: () -> Unit,
) {
    val context: Context = LocalContext.current

    if (recordingSaved) {
        Toast.makeText(
            context,
            "Audio saved",
            Toast.LENGTH_LONG
        ).show()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.padding(top = 20.dp)) {
            AudioRecorderTopBar(
                navigateToRecords = { navigateToAudios() },
                closeRecorder = { navigateBack() })
        }


        AudioWaveform(amplitudes = amplitudeList)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            StartStopButton(
                recording = recording,
                start = { startRecording() },
                stop = { stopRecording() })

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AudioRecordingScreenPreview() {
    AudioRecordingScreen(
        recording = true,
        recordingSaved = false,
        amplitudeList = listOf(100, 200, 300, 5000, 100),
        startRecording = { /*TODO*/ },
        stopRecording = { /*TODO*/ },
        navigateToAudios = {},
        navigateBack = {},
    )
}

@Preview(showSystemUi = true)
@Composable
fun AudioRecordingScreenIdlePreview() {
    AudioRecordingScreen(
        recording = false,
        recordingSaved = false,
        amplitudeList = listOf(0, 0, 0, 0, 0),
        startRecording = { /*TODO*/ },
        stopRecording = { /*TODO*/ },
        navigateToAudios = {},
        navigateBack = {},
    )
}
