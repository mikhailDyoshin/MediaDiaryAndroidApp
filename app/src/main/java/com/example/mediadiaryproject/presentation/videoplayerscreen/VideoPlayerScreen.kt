package com.example.mediadiaryproject.presentation.videoplayerscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun VideoPlayerScreen(
    navigator: DestinationsNavigator
) {
    Column {
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