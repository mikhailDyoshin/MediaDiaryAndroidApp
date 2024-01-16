package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.destinations.TextNoteEditScreenDestination
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
            TextNote(
                note = note,
                deleteNote = { noteToDelete ->
                    viewModel.deleteNote(
                        note = noteToDelete,
                        dayId = dayId
                    )
                },
                navigateToEditScreen = { id ->
                    navigator.navigate(
                        TextNoteEditScreenDestination(
                            textNoteToEditId = id,
                            dayId = dayId
                        )
                    )
                }
            )

        }
    }

}

@Composable
private fun TextNote(
    note: TextNoteState,
    deleteNote: (noteToDelete: TextNoteState) -> Unit,
    navigateToEditScreen: (noteId: Int) -> Unit
) {
    Row {
        Column {
            Text(text = note.date)
            Text(text = note.title)
            Text(text = note.text)
        }
        Row {
            Button(onClick = { navigateToEditScreen(note.id) }) {
                Text(text = "Edit")
            }
            Button(onClick = { deleteNote(note) }) {
                Text(text = "Delete")
            }
        }
    }

}
