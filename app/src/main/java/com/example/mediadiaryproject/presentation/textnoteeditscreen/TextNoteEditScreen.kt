package com.example.mediadiaryproject.presentation.textnoteeditscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.textnoteeditscreen.components.NoteTextField
import com.example.mediadiaryproject.presentation.textnoteeditscreen.components.TextNoteScreenButton
import com.example.mediadiaryproject.presentation.textnoteeditscreen.components.TitleTextField


@Composable
fun TextNoteEditScreen(
    noteTitle: String,
    noteText: String,
    textNoteToEditId: Int,
    dayId: Int,
    setNoteData: (noteId: Int) -> Unit,
    updateDayId: (dayId: Int) -> Unit,
    updateTitle: (title: String) -> Unit,
    updateNoteText: (text: String) -> Unit,
    updateNote: (noteId: Int) -> Unit,
    saveNote: () -> Unit,
    navigateToNotes: (dayId: Int) -> Unit,
) {

    if (textNoteToEditId != -1) {
        LaunchedEffect(true) {
            setNoteData(textNoteToEditId)
        }
    } else {
        updateDayId(dayId)
    }

    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
        TitleTextField(
            title = noteTitle,
            updateTitle = { title -> updateTitle(title) }
        )
        NoteTextField(
            note = noteText,
            updateNote = { value -> updateNoteText(value) },
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextNoteScreenButton(
                text = "Save",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                onClick = {
                    if (textNoteToEditId != -1) {
                        updateNote(textNoteToEditId)
                    } else saveNote()
                })
            TextNoteScreenButton(
                text = "Notes",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                onClick = {
                    navigateToNotes(dayId)
                })
        }

    }

}


@Preview(showSystemUi = true)
@Composable
fun TextNoteEditScreenPreview() {
    TextNoteEditScreen(
        noteTitle = "My note",
        noteText = "Note text text text text text text text text text text text text text text text ",
        textNoteToEditId = 1,
        dayId = 1,
        setNoteData = {},
        updateDayId = {},
        updateTitle = {},
        updateNoteText = {},
        updateNote = {},
        saveNote = {},
        navigateToNotes = {}
    )
}
