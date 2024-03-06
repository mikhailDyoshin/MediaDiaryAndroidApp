package com.example.mediadiaryproject.presentation.videoplayerscreen


import android.util.Log
import androidx.activity.compose.BackHandler
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
import com.example.mediadiaryproject.presentation.videoplayerscreen.components.VideoPlayerWarningWindow
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

    val editModeOn = viewModel.editMode.value

    BackHandler(enabled = true, onBack = {
        navigateUpLogic(navigator = navigator, editModeState = editModeOn) {
            viewModel.displayWarningWindow()
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (viewModel.warningWindowState.value) {
            VideoPlayerWarningWindow(
                onDiscard = {
                    viewModel.closeWarningWindow()
                    viewModel.turnOffEditMode()
                    viewModel.hideInfo()
                },
                onSave = {
                    viewModel.closeWarningWindow()
                    viewModel.saveEditedInfo()
                },
                onCancel = { viewModel.closeWarningWindow() })
        }

        if (viewModel.menuState.value) {
            // Video-player's top bar with back and info buttons
            VideoPlayerTopBar(
                navigateBack = { navigator.navigateUp() },
                showInfo = { viewModel.displayInfo() },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        if (viewModel.infoState.value) {
            // Video's title with description
            VideoInfo(
                editMode = editModeOn,
                title = viewModel.titleState.value,
                description = viewModel.descriptionState.value,
                updateTitle = { title -> viewModel.updateTitle(title) },
                updateDescription = { description -> viewModel.updateDescription(description) },
                closeMenu = { viewModel.hideInfo() },
                turnOnEditMode = { viewModel.turnOnEditMode() },
                saveInfo = { viewModel.saveEditedInfo() })
        }
        // The video player
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

private fun navigateUpLogic(
    navigator: DestinationsNavigator,
    editModeState: Boolean,
    callback: () -> Unit
) {
    if (editModeState) {
        callback()
        Log.d("Warning window", "Warning window is displayed")
    } else {
        navigator.navigateUp()
    }
}
