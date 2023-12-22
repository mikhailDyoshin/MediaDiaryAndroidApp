package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.destinations.VideoPlayerScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TextNoteScreen(
    navigator: DestinationsNavigator,
) {


    Column {
        Text(text = "Text note screen")
    }
}