package com.example.mediadiaryproject.presentation.photoview.components


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediadiaryproject.presentation.warningwindow.WarningWindow

@Composable
fun PhotoViewWarningWindow(
    onDiscard: () -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    WarningWindow(
        message = "Save your changes or discard them?",
        onDiscard = { onDiscard() },
        onSave = { onSave() },
        onCancel = { onCancel() },
        modifier = modifier
    )

}

@Preview(showSystemUi = true)
@Composable
fun PhotoViewWarningWindowPreview() {
    PhotoViewWarningWindow(
        onSave = {},
        onCancel = {},
        onDiscard = {}
    )
}