package com.example.mediadiaryproject.presentation.videoplayerscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.camerascreen.viewmodel.CameraViewModel
import com.example.mediadiaryproject.presentation.videoplayerscreen.viewmodel.VideoPlayerScreenViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun VideoPlayerScreen(
    navigator: DestinationsNavigator,
    viewModel: VideoPlayerScreenViewModel = hiltViewModel()
) {

    val listOfFiles = viewModel.state.value

    Column {
        Column {
            for (file in listOfFiles) {
                Text(file.fileName)
            }
        }
        Text(text = "Messages screen")
        Button(
            onClick = {
                // Navigates back to Home screen
                navigator.popBackStack()
            }
        ) {
            Text(text = "Navigate back")
        }
    }
}