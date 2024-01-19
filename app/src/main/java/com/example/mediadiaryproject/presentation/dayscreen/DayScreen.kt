package com.example.mediadiaryproject.presentation.dayscreen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.dayscreen.components.AddContentButton
import com.example.mediadiaryproject.presentation.dayscreen.components.DayButton


@Composable
fun DayScreen(
    date: DateState,
    navigateToTextEditScreen: () -> Unit,
    navigateToAudioRecordScreen: () -> Unit,
    navigateToCameraScreen: () -> Unit,
    navigateToDayContent: () -> Unit,

    ) {

    val verticalShiftForSideButtons = -32
    val horizontalShiftForSideButtons = -32

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (dayButton, addTextButton, addAudioButton, addPhotoVideoButton) = createRefs()

        AddContentButton(
            onClick = { navigateToTextEditScreen() },
            modifier = Modifier.constrainAs(addTextButton) {
                this.top.linkTo(dayButton.bottom, margin = verticalShiftForSideButtons.dp)
                this.end.linkTo(dayButton.start, margin = horizontalShiftForSideButtons.dp)
            },
            iconId = R.drawable.notes_icon
        )
        AddContentButton(
            onClick = { navigateToAudioRecordScreen() },
            modifier = Modifier.constrainAs(addAudioButton) {
                this.top.linkTo(dayButton.bottom, margin = 20.dp)
                this.centerHorizontallyTo(dayButton)
            },
            iconId = R.drawable.microphone_icon
        )
        AddContentButton(
            onClick = { navigateToCameraScreen() },
            modifier = Modifier.constrainAs(addPhotoVideoButton) {
                this.top.linkTo(dayButton.bottom, margin = verticalShiftForSideButtons.dp)
                this.start.linkTo(dayButton.end, margin = horizontalShiftForSideButtons.dp)
            },
            iconId = R.drawable.camera_icon
        )
        DayButton(
            date = date,
            navigateToDayContent = { navigateToDayContent() },
            modifier = Modifier.constrainAs(dayButton) {
                centerTo(parent)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DayScreenPreview() {
    DayScreen(
        date = DateState(day = "18", month = "Sep"),
        navigateToTextEditScreen = {},
        navigateToAudioRecordScreen = {},
        navigateToCameraScreen = {},
        navigateToDayContent = {}
    )
}
