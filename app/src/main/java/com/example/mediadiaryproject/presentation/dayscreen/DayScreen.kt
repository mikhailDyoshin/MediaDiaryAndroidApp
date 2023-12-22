package com.example.mediadiaryproject.presentation.dayscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.destinations.AudioRecordingScreenDestination
import com.example.mediadiaryproject.presentation.destinations.MainContentDestination
import com.example.mediadiaryproject.presentation.destinations.TextNoteScreenDestination
import com.example.mediadiaryproject.presentation.destinations.VideoPlayerScreenDestination
import com.example.mediadiaryproject.presentation.mainscreen.MAIN_SCREEN_ROUTE
import com.example.mediadiaryproject.presentation.navgraph.MediaDiaryNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@MediaDiaryNavGraph(start = true)
@Destination(route = MAIN_SCREEN_ROUTE)
@Composable
fun DayScreen(
    navigator: DestinationsNavigator,
) {


    Column {
        Button(onClick = { navigator.navigate(TextNoteScreenDestination()) }) {
            Text(text = "Add text note")
        }
        Button(onClick = { navigator.navigate(AudioRecordingScreenDestination()) }) {
            Text(text = "Add audio note")
        }
        Button(onClick = { navigator.navigate(MainContentDestination()) }) {
            Text(text = "Add photo/video note")
        }
    }
}