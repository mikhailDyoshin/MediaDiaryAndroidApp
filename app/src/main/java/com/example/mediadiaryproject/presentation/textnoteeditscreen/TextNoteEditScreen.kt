package com.example.mediadiaryproject.presentation.textnoteeditscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.destinations.TextNotesScreenDestination
import com.example.mediadiaryproject.presentation.textnoteeditscreen.viewmodel.TextNoteEditScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TextNoteEditScreen(
    textNoteToEditId: Int = -1,
    navigator: DestinationsNavigator,
    viewModel: TextNoteEditScreenViewModel = hiltViewModel()
) {

    if (textNoteToEditId != -1) {
        LaunchedEffect(true) {
            viewModel.setNoteData(textNoteToEditId)
        }

    }

    Column {
        Text(text = "Text note screen")
        Text(text = viewModel.date)
        TextField(
            value = viewModel.title,
            onValueChange = { title -> viewModel.updateTitle(title) }
        )
        TextField(
            value = viewModel.text,
            onValueChange = { text -> viewModel.updateText(text) },
            maxLines = 5
        )
        Row() {
            if (textNoteToEditId != -1) {
                Button(onClick = {
                    viewModel.updateNote(textNoteToEditId)
                }) {
                    Text(text = "Edit")
                }
            } else {
                Button(onClick = { viewModel.saveNote() }) {
                    Text(text = "Save")
                }
            }
            Button(onClick = { navigator.navigate(TextNotesScreenDestination()) }) {
                Text(text = "Today's notes")
            }
        }

    }

}
