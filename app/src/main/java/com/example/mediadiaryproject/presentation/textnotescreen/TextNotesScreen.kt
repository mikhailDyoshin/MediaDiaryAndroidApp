package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.destinations.TextNoteEditScreenWrapperDestination
import com.example.mediadiaryproject.presentation.textnotescreen.components.TextNoteItem
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteState
import com.example.mediadiaryproject.presentation.textnotescreen.viewmodel.TextNotesScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TextNotesScreen(
    navigator: DestinationsNavigator,
    viewModel: TextNotesScreenViewModel = hiltViewModel(),
    dayId: Int,
) {

    val notes = viewModel.state.value

    LaunchedEffect(true) {
        viewModel.getNotesByDay(dayId)
    }

    Column {
        for (note in notes) {
            TextNoteItem(
                note = note,
                deleteNote = { noteToDelete ->
                    viewModel.deleteNote(
                        note = noteToDelete,
                        dayId = dayId
                    )
                },
                navigateToEditScreen = { id ->
                    navigator.navigate(
                        TextNoteEditScreenWrapperDestination(
                            textNoteToEditId = id,
                            dayId = dayId
                        )
                    )
                }
            )

        }
    }

}
