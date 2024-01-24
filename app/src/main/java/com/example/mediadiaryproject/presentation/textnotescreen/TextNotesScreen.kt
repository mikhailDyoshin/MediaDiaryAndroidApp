package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.textnotescreen.components.TextNoteItem
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun TextNotesScreen(
    notes: List<TextNoteState>,
    deleteNote: (TextNoteState) -> Unit,
    editNote: (noteId: Int) -> Unit,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
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

@Preview(showSystemUi = true)
@Composable
fun TextNotesScreenPreview() {

    val note1 = TextNoteState(
        id = 0,
        date = "12 Jan, 2024",
        title = "My note 1",
        text = "Preview preview preview preview preview preview preview preview preview preview ..."
    )

    val note2 = TextNoteState(
        id = 1,
        date = "14 Jan, 2024",
        title = "My note 2",
        text = "Preview preview preview preview preview preview preview preview preview preview ..."
    )

    val note3 = TextNoteState(
        id = 2,
        date = "17 Jan, 2024",
        title = "My note 3",
        text = "Preview preview preview preview preview preview preview preview preview preview ..."
    )

    TextNotesScreen(notes = listOf(note1, note2, note3), deleteNote = {}, editNote = {})
}
