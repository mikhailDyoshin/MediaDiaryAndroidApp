package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState
import com.example.mediadiaryproject.ui.theme.AudioListBackgroundColor


@Composable
fun AudiosList(
    listOfAudios: List<AudioFileState>,
    playAudio: (audio: AudioFileState) -> Unit,
    pauseAudio: () -> Unit,
    currentPosition: Float,
    seekTo: (position: Float) -> Unit
) {
    Column(Modifier.fillMaxSize().padding(horizontal = 10.dp)) {
        listOfAudios.forEach {audio ->
            AudioItem(
                audio,
                playAudio = { audioItem -> playAudio(audioItem) },
                pauseAudio = { pauseAudio() },
                currentPosition = currentPosition,
                seekTo = { position -> seekTo(position) }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AudiosListPreview() {

    val listOfAudios = listOf(
        AudioFileState(
            fileName = "My audio long long long long title",
            mediaItem = null,
            underFocus = true,
            isPlaying = true
        ),
        AudioFileState(
            fileName = "My audio long long long long title",
            mediaItem = null,
            underFocus = false,
            isPlaying = false
        ),
        AudioFileState(
            fileName = "My audio long long long long title",
            mediaItem = null,
            underFocus = false,
            isPlaying = false
        ),
        AudioFileState(
            fileName = "My audio long long long long title",
            mediaItem = null,
            underFocus = false,
            isPlaying = false
        ),
    )

    AudiosList(
        listOfAudios = listOfAudios,
        playAudio = {},
        pauseAudio = { /*TODO*/ },
        currentPosition = 0.3f,
        seekTo = {}
    )
}