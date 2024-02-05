package com.example.mediadiaryproject.presentation.videoslistscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.videoslistscreen.state.VideoListItemState
import com.example.mediadiaryproject.presentation.videoslistscreen.viewmodel.VideosListScreenViewModel
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

    val listOfFileNames = viewModel.state.value.map {
        VideoListItemState(
            videoId = it.videoId,
            videoFileName = it.fileName,
            videoFrame = it.preview
        )
    }

    Column {
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

@Preview(showSystemUi = true)
@Composable
fun VideosListScreenPreview() {
    VideosListScreenCore(
        videos = listOf(
            VideoListItemState(
                videoId = 0,
                videoFileName = "Video 1",
                videoFrame = null
            ),
            VideoListItemState(
                videoId = 1,
                videoFileName = "Video 2",
                videoFrame = null

            ),
            VideoListItemState(
                videoId = 2,
                videoFileName = "Video 3",
                videoFrame = null

            )
        ), playVideo = {})
}