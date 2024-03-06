package com.example.mediadiaryproject.presentation.videoslistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.videoslistscreen.viewmodel.VideosListScreenViewModel
import com.example.mediadiaryproject.ui.theme.AudioListBackgroundColor
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun VideosListScreen(
    navigator: DestinationsNavigator,
    viewModel: VideosListScreenViewModel = hiltViewModel(),
    dayId: Int,
) {

    LaunchedEffect(true) {
        viewModel.getVideosList(dayId = dayId)
    }

    val listOfFileNames = viewModel.state.value

    Column(modifier = Modifier.background(color = AudioListBackgroundColor)) {
        VideosListScreenCore(
            videos = listOfFileNames,
            playVideo = { videoId ->
                navigator.navigate(
                    com.example.mediadiaryproject.presentation.destinations.VideoPlayerScreenDestination(
                        videoId = videoId
                    )
                )
            })
    }
}
