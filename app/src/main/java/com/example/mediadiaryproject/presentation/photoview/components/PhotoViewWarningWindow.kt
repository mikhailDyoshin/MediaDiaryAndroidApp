package com.example.mediadiaryproject.presentation.photoview.components


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediadiaryproject.presentation.warningwindow.WarningWindow

@Composable
fun PhotoViewWarningWindow(
    message: String,
    onDiscard: () -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    WarningWindow(
        message = message,
        onDiscard = { onDiscard() },
        onSave = { onSave() },
        onCancel = { onCancel() }
    )

}

@Preview(showSystemUi = true)
@Composable
fun PhotoViewWarningWindowPreview() {
    PhotoViewWarningWindow(
        message = "Save your changes or discard them?",
        onSave = {},
        onCancel = {},
        onDiscard = {}
    )
}