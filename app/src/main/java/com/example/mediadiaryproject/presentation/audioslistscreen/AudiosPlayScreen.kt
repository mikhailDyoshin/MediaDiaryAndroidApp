package com.example.mediadiaryproject.presentation.audioslistscreen

import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.example.mediadiaryproject.presentation.audioslistscreen.components.AudiosList
import com.example.mediadiaryproject.presentation.audioslistscreen.viewmodel.AudioPlayerViewModel
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
        AudiosList(
            listOfAudios,
            playAudio = { audio -> viewModel.playAudio(audio) },
            pauseAudio = { viewModel.pauseAudio() },
            currentPosition = viewModel.currentAudioPositionState.floatValue,
            seekTo = { position -> viewModel.seekTo(position) }
        )
    }
}




