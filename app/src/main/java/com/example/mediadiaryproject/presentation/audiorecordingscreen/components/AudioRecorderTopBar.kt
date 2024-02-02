package com.example.mediadiaryproject.presentation.audiorecordingscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R

@Composable
fun AudioRecorderTopBar(navigateToRecords: () -> Unit, closeRecorder: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical =  10.dp)
    ) {
        IconButton(onClick = { closeRecorder() }, modifier = Modifier.size(30.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.close_icon),
                contentDescription = "close icon"
            )
        }
        IconButton(onClick = { navigateToRecords() }, modifier = Modifier.size(30.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.audio_records),
                contentDescription = "audio-records icon"
            )
        }
    }
}

@Preview
@Composable
fun AudioRecorderTopBarPreview() {
    AudioRecorderTopBar(navigateToRecords = {}, closeRecorder = {})
}

