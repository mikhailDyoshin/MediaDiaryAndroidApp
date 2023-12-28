package com.example.mediadiaryproject.presentation.audiosplayscreen

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerControlView
import com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel.AudioPlayerViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@OptIn(UnstableApi::class) @Composable
fun AudiosPlayScreen(viewModel: AudioPlayerViewModel = hiltViewModel()) {
//    AndroidView(factory = { context ->
//        PlayerControlView(context).also {
//            it.player = viewModel.player
//        }
//    }, modifier = Modifier.height(200.dp))
    Column {
        Text(text = "Audios screen")
    }
}