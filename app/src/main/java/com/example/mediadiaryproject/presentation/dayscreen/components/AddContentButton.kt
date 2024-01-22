package com.example.mediadiaryproject.presentation.dayscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R

@Composable
fun AddContentButton(onClick: () -> Unit, modifier: Modifier, iconId: Int) {

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                .clickable(onClick = onClick)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Icon(painter = painterResource(id = iconId), contentDescription = "Add-content-button's icon")
            }

        }
    }

}

@Preview
@Composable
fun AddPhotoVideoPreview() {
    AddContentButton(onClick = {}, modifier = Modifier, iconId = R.drawable.camera_icon)
}

@Preview
@Composable
fun AddAudioPreview() {
    AddContentButton(onClick = {}, modifier = Modifier, iconId = R.drawable.microphone_icon)
}

@Preview
@Composable
fun AddTextNotePreview() {
    AddContentButton(onClick = {}, modifier = Modifier, iconId = R.drawable.notes_icon)
}
