package com.example.mediadiaryproject.presentation.dayscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.destinations.AudioRecorderWrapperDestination
import com.example.mediadiaryproject.presentation.destinations.CameraWrapperDestination
import com.example.mediadiaryproject.presentation.destinations.TextNoteEditScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DayScreenWrapper(
    navigator: DestinationsNavigator,
    dayId: Int,
) {

    Column {
        Text(text = "Id = $dayId")
        DayScreen(
            date = DateState(day = "19", month = "Jan"),
            navigateToTextEditScreen = {
                navigator.navigate(TextNoteEditScreenDestination(dayId = dayId))
            },
            navigateToAudioRecordScreen = {
                navigator.navigate(AudioRecorderWrapperDestination(dayId = dayId))
            },
            navigateToCameraScreen = {
                navigator.navigate(CameraWrapperDestination(dayId = dayId))
            },
            navigateToDayContent = {}
            )
    }
}