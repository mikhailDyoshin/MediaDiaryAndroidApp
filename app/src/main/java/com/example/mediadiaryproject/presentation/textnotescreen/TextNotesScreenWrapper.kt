package com.example.mediadiaryproject.presentation.textnotescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.destinations.TextNoteEditScreenWrapperDestination
import com.example.mediadiaryproject.presentation.textnotescreen.viewmodel.TextNotesScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TextNotesScreenWrapper(
    navigator: DestinationsNavigator,
    viewModel: TextNotesScreenViewModel = hiltViewModel(),
    dayId: Int,
) {

    val notes = viewModel.state.value

    LaunchedEffect(true) {
        viewModel.getNotesByDay(dayId)
    }

    TextNotesScreen(
        notes = notes,
        deleteNote = { note -> viewModel.deleteNote(note, dayId) },
        editNote = { noteId ->
            navigator.navigate(
                TextNoteEditScreenWrapperDestination(
                    dayId = dayId,
                    textNoteToEditId = noteId
                )
            )
        },
        addNewNote = {
            navigator.navigate(
                TextNoteEditScreenWrapperDestination(
                    dayId = dayId,
                )
            )
        }
    )
}
