package com.example.mediadiaryproject.presentation.textnoteeditscreen.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextNoteScreenButton(text: String, onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(text = text)
    }
}

@Preview
@Composable
fun TextNoteScreenButtonPreview() {
    TextNoteScreenButton(text = "Save", onClick = {})
}
