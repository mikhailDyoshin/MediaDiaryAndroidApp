package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState

@Composable
fun AudiosList(
    listOfAudios: List<AudioFileState>,
    playAudio: (audio: AudioFileState) -> Unit,
    pauseAudio: () -> Unit,
    currentPosition: Float,
    seekTo: (position: Float) -> Unit
) {
    Column {
        for (audio in listOfAudios) {
            AudioItem(
                audio,
                playAudio = { audioItem -> playAudio(audioItem) },
                pauseAudio = { pauseAudio() },
                isPlaying = audio.underFocus,
                currentPosition = currentPosition,
                seekTo = { position -> seekTo(position) }
            )
        }
    }
}