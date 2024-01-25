package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.textnotescreen.components.AddItemButton
import com.example.mediadiaryproject.presentation.textnotescreen.components.TextNoteItem
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteState
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TextNotesScreen(
    notes: List<TextNoteState>,
    deleteNote: (TextNoteState) -> Unit,
    editNote: (noteId: Int) -> Unit,
    addNewNote: () -> Unit,
) {
    Scaffold(bottomBar = {
        AddItemButton(modifier = Modifier, onClick = { addNewNote() })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(6f)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
//            .background(color = Color.White)
            ) {
                notes.forEach { note ->
                    TextNoteItem(
                        note = note,
                        deleteNote = { noteToDelete ->
                            deleteNote(noteToDelete)
                        },
                        navigateToEditScreen = { id ->
                            editNote(id)
                        }
                    )

                }


            }


        }

    }


}

@Preview(showSystemUi = true)
@Composable
fun TextNotesScreenPreview() {

    val note1 = TextNoteState(
        id = 0,
        date = "",
        title = "My note 1",
        text = "Preview preview preview preview preview preview preview preview preview preview ..."
    )

    val note2 = TextNoteState(
        id = 1,
        date = "",
        title = "My note 2",
        text = "Preview preview preview preview preview preview preview preview preview preview ..."
    )

    val note3 = TextNoteState(
        id = 2,
        date = "",
        title = "My note 3",
        text = "Preview preview preview preview preview preview preview preview preview preview ..."
    )

    TextNotesScreen(
        notes = listOf(note1, note2, note3),
        deleteNote = {},
        editNote = {},
        addNewNote = {})
}
