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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState
import com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel.AudioPlayerViewModel
import com.ramcosta.composedestinations.annotation.Destination

@RequiresApi(Build.VERSION_CODES.S)
@Destination
@OptIn(UnstableApi::class)
@Composable
fun AudiosPlayScreen(viewModel: AudioPlayerViewModel = hiltViewModel(), dayId: Int) {


    LaunchedEffect(true) {
        viewModel.getListOfAudios(dayId)
    }

    val listOfAudios = viewModel.state.value

    Column {
        Text(text = "Audios screen")
        AudiosColumn(
            listOfAudios,
            playAudio = { audio -> viewModel.playAudio(audio) },
            pauseAudio = { viewModel.pauseAudio() },
            currentPosition = viewModel.currentAudioPositionState.floatValue,
            seekTo = { position -> viewModel.seekTo(position) }
        )
    }
}

@Composable
private fun AudiosColumn(
    listOfAudios: List<AudioFileState>,
    playAudio: (audio: AudioFileState) -> Unit,
    pauseAudio: () -> Unit,
    currentPosition: Float,
    seekTo: (position: Float) -> Unit
) {
    Column {
        for (audio in listOfAudios) {
            AudioRow(
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

@Composable
private fun AudioRow(
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
            Slider(currentPosition, seekTo = { position -> seekTo(position) })
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = Color.Gray)
        )

    }

}

@Composable
private fun PlayPauseButton(
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

@Composable
private fun Slider(currentPosition: Float, seekTo: (position: Float) -> Unit) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = currentPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { seekTo(sliderPosition) }
        )
        Text(text = currentPosition.toString())
    }
}