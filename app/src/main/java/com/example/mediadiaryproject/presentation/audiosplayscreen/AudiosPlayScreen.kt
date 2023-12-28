package com.example.mediadiaryproject.presentation.audiosplayscreen

import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState
import com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel.AudioPlayerViewModel
import com.ramcosta.composedestinations.annotation.Destination

@RequiresApi(Build.VERSION_CODES.S)
@Destination
@OptIn(UnstableApi::class)
@Composable
fun AudiosPlayScreen(viewModel: AudioPlayerViewModel = hiltViewModel()) {

    val listOfAudios = viewModel.state.value

    Column {
        Text(text = "Audios screen")
        AudiosColumn(listOfAudios, playAudio = { audio -> viewModel.playAudio(audio)})
    }
}

@Composable
private fun AudiosColumn(listOfAudios: List<AudioFileState>, playAudio: (audio: MediaItem) -> Unit) {
    Column() {
        for (audio in listOfAudios) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { playAudio(audio.mediaItem) }) {
                    Text(text = "Play")
                }
                Text(
                    audio.fileName,
                    modifier = Modifier.padding(start = 10.dp))
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(color = Color.Gray))

        }
    }
}