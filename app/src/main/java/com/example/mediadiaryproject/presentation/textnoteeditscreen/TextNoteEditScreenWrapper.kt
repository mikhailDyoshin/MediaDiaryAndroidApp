package com.example.mediadiaryproject.presentation.textnoteeditscreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.destinations.TextNotesScreenDestination
import com.example.mediadiaryproject.presentation.textnoteeditscreen.viewmodel.TextNoteEditScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TextNoteEditScreenWrapper(
    textNoteToEditId: Int = -1,
    navigator: DestinationsNavigator,
    viewModel: TextNoteEditScreenViewModel = hiltViewModel(),
    dayId: Int,
) {

    TextNoteEditScreen(
        noteTitle = viewModel.title,
        noteText = viewModel.text,
        dayId = dayId,
        textNoteToEditId = textNoteToEditId,
        setNoteData = { data -> viewModel.setNoteData(data) },
        updateDayId = { id -> viewModel.updateDayId(id) },
        updateTitle = { title -> viewModel.updateTitle(title) },
        updateNoteText = { text -> viewModel.updateText(text) },
        updateNote = { id -> viewModel.updateNote(id) },
        saveNote = { viewModel.saveNote() },
        navigateToNotes = { navigator.navigate(TextNotesScreenDestination(dayId = dayId)) }
    )

}