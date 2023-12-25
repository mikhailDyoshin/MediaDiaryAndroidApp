package com.example.mediadiaryproject.presentation.audiorecordingscreen


import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.mediadiaryproject.presentation.audiorecordingscreen.audiorecorder.MediaDiaryAudioRecorder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.io.File

@Destination
@Composable
fun AudioRecordingScreen(
    navigator: DestinationsNavigator,
) {
    val context: Context = LocalContext.current

    val recorder: MediaDiaryAudioRecorder by lazy {
        MediaDiaryAudioRecorder(context)
    }

    var audioFile: File? = null

    Column {
        Text(text = "Audio recording screen")
        Button(onClick = {
            File(context.filesDir, "my_recording.mp3").also {
                recorder.start(it)
                audioFile = it

            }
        }) {
            Text(text = "Start")
        }
        Button(onClick = {
            recorder.stop()
        }) {
            Text(text = "Stop")
        }
    }
}