package com.example.mediadiaryproject.presentation.textnoteeditscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.ui.theme.BlueText
import com.example.mediadiaryproject.ui.theme.ContainerColor

@Composable
fun TextNoteScreenButton(text: String, onClick: () -> Unit, modifier: Modifier) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier,
        border = BorderStroke(
            width = 2.dp,
            brush = Brush.linearGradient(listOf(BlueText, ContainerColor))
        )
    ) {
        Text(text = text, color = BlueText, fontSize = 20.sp)
    }
}

@Preview
@Composable
fun TextNoteScreenButtonPreview() {
    TextNoteScreenButton(text = "Save", onClick = {}, modifier = Modifier.fillMaxWidth())
}
