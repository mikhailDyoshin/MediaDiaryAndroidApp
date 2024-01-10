package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteState
import com.example.mediadiaryproject.presentation.textnotescreen.viewmodel.TextNotesScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TextNotesScreen(
    navigator: DestinationsNavigator,
    viewModel: TextNotesScreenViewModel = hiltViewModel()
) {

    val notes = viewModel.state.value

    Column {
        for (note in notes) {
            TextNote(note = note)
        }
    }

}

@Composable
private fun TextNote(note: TextNoteState) {
    Column {
        Text(text = note.date)
        Text(text = note.title)
        Text(text = note.text)
    }
}
