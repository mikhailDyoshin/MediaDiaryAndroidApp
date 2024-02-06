package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState

@Composable
fun PlayPauseButton(
    audio: AudioFileState,
    play: (audio: AudioFileState) -> Unit,
    pause: (audio: AudioFileState) -> Unit
) {

    val isPlaying = audio.isPlaying

    val buttonText = if (isPlaying) {
        "Pause"
    } else {
        "Play"
    }

    Button(onClick = {
        if (isPlaying) {
            pause(audio)
        } else {
            play(audio)
        }
    }) {
        Text(text = buttonText)
    }
}