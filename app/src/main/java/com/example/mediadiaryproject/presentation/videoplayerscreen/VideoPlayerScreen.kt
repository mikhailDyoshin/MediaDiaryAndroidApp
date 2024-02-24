package com.example.mediadiaryproject.presentation.videoplayerscreen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView
import com.example.mediadiaryproject.presentation.videoplayerscreen.components.VideoInfo
import com.example.mediadiaryproject.presentation.videoplayerscreen.components.VideoPlayerTopBar
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
        val job = viewModel.getVideoItem(videoId)

        job.join()

        viewModel.playVideo()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (viewModel.menuState.value) {
            VideoPlayerTopBar(
                navigateBack = { navigator.navigateUp() },
                showInfo = { viewModel.displayInfo() },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        if (viewModel.infoState.value) {
            VideoInfo(
                editMode = false,
                title = viewModel.state.value?.title ?: "",
                description = viewModel.state.value?.description ?: "",
                updateTitle = { /*TODO*/ },
                updateDescription = { /*TODO*/ },
                closeMenu = { viewModel.hideInfo() },
                turnOnEditMode = { /*TODO*/ },
                saveInfo = { /*TODO*/ })
        }
        AndroidView(
            factory = { context ->
                PlayerView(context).also {
                    it.player = viewModel.player
                    it.setOnClickListener {
                        viewModel.toggleMenu()
                        Log.d("Video player", "Clicked!")
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        )
    }
}
