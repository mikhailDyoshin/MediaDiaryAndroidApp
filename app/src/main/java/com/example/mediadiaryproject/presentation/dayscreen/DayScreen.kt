package com.example.mediadiaryproject.presentation.dayscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.destinations.AudioRecorderWrapperDestination
import com.example.mediadiaryproject.presentation.destinations.MainContentDestination
import com.example.mediadiaryproject.presentation.destinations.TextNoteEditScreenDestination
import com.example.mediadiaryproject.presentation.navgraph.MediaDiaryNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

const val MAIN_SCREEN_ROUTE = "main"

@MediaDiaryNavGraph(start = true)
@Destination(route = MAIN_SCREEN_ROUTE)
@Composable
fun DayScreen(
    navigator: DestinationsNavigator,
) {

    Column {
        Button(onClick = { navigator.navigate(TextNoteEditScreenDestination()) }) {
            Text(text = "Add text note")
        }
        Button(onClick = { navigator.navigate(AudioRecorderWrapperDestination()) }) {
            Text(text = "Add audio note")
        }
        Button(onClick = { navigator.navigate(MainContentDestination()) }) {
            Text(text = "Add photo/video note")
        }
    }
}