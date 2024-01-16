package com.example.mediadiaryproject.presentation.dayscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.destinations.AudioRecorderWrapperDestination
import com.example.mediadiaryproject.presentation.destinations.MainContentDestination
import com.example.mediadiaryproject.presentation.destinations.TextNoteEditScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun DayScreen(
    navigator: DestinationsNavigator,
    dayId: Int,
) {

    Column {
        Text(text = "Id = $dayId")
        Button(onClick = { navigator.navigate(TextNoteEditScreenDestination(dayId = dayId)) }) {
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