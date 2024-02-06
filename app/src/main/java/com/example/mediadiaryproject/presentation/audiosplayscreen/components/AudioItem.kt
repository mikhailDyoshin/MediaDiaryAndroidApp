package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState

@Composable
fun AudioItem(
    audio: AudioFileState,
    playAudio: (audio: AudioFileState) -> Unit,
    pauseAudio: () -> Unit,
    isPlaying: Boolean,
    currentPosition: Float,
    seekTo: (position: Float) -> Unit
) {


    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PlayPauseButton(
                audio = audio,
                play = { audio -> playAudio(audio) },
                pause = { pauseAudio() })
            Text(
                audio.fileName,
                modifier = Modifier.padding(start = 10.dp)
            )
        }


        if (isPlaying) {
            ProgressBar(currentPosition, seekTo = { position -> seekTo(position) })
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = Color.Gray)
        )

    }

}