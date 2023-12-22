package com.example.mediadiaryproject.presentation.audiorecordingscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AudioRecordingScreen(
    navigator: DestinationsNavigator,
) {


    Column {
        Text(text = "Audio recording screen")
    }
}