package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState

@Composable
fun PlayPauseButton(
    audio: AudioFileState,
    play: (audio: AudioFileState) -> Unit,
    pause: (audio: AudioFileState) -> Unit
) {

    val isPlaying = audio.isPlaying

    val buttonIcon = if (isPlaying) {
        R.drawable.pause_icon
    } else {
        R.drawable.play_icon
    }

    IconButton(onClick = {
        if (isPlaying) {
            pause(audio)
        } else {
            play(audio)
        }
    }) {
        Icon(
            painter = painterResource(id = buttonIcon),
            contentDescription = "play/pause-icon"
        )
    }
}


@Preview
@Composable
fun PlayPauseButtonPlayingPreview() {
    PlayPauseButton(audio = AudioFileState(
        fileName = "My audio long long long long title",
        mediaItem = null,
        underFocus = true,
        isPlaying = true
    ),
        play = {},
        pause = {}
    )
}

@Preview
@Composable
fun PlayPauseButtonIdlePreview() {
    PlayPauseButton(audio = AudioFileState(
        fileName = "My audio long long long long title",
        mediaItem = null,
        underFocus = true,
        isPlaying = false
    ),
        play = {},
        pause = {}
    )
}