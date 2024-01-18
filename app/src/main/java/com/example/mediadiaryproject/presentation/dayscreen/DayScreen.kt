package com.example.mediadiaryproject.presentation.dayscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediadiaryproject.presentation.dayscreen.components.DayButton


@Composable
fun DayScreen(
    date: DateState,
    navigateToTextEditScreen: () -> Unit,
    navigateToAudioRecordScreen: () -> Unit,
    navigateToCameraScreen: () -> Unit,
    navigateToDayContent: () -> Unit,

    ) {

    Column {
        Button(onClick = { navigateToTextEditScreen() }) {
            Text(text = "Add text note")
        }
        Button(onClick = { navigateToAudioRecordScreen() }) {
            Text(text = "Add audio note")
        }
        DayButton(date = date) {
            navigateToDayContent()
        }
        Button(onClick = { navigateToCameraScreen() }) {
            Text(text = "Add photo/video note")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayScreenPreview() {
    DayScreen(
        date = DateState(day = "18", month = "Dec"),
        navigateToTextEditScreen = {},
        navigateToAudioRecordScreen = {},
        navigateToCameraScreen = {},
        navigateToDayContent = {}
    )
}
