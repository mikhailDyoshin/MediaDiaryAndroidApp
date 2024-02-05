package com.example.mediadiaryproject.presentation.videoplayerscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView
import com.example.mediadiaryproject.presentation.videoplayerscreen.viewmodel.VideoPlayerScreenViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun VideoPlayerScreen(
    navigator: DestinationsNavigator,
    viewModel: VideoPlayerScreenViewModel = hiltViewModel(),
    videoId: Int,
) {

    LaunchedEffect(true) {
        viewModel.playVideo(videoId = videoId)
    }

    Column {
        AndroidView(factory = { context ->
            PlayerView(context).also {
                it.player = viewModel.player
            }
        }, modifier = Modifier.height(200.dp))
    }
}