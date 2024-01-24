package com.example.mediadiaryproject.presentation.textnotescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteState
import com.example.mediadiaryproject.ui.theme.DeleteButtonColor
import com.example.mediadiaryproject.ui.theme.EditButtonColor

@Composable
fun TextNoteItem(
    note: TextNoteState,
    deleteNote: (noteToDelete: TextNoteState) -> Unit,
    navigateToEditScreen: (noteId: Int) -> Unit
) {

    val cornerSize = 20.dp

    Column(modifier = Modifier.shadow(4.dp, shape = RoundedCornerShape(cornerSize), clip = true)) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(cornerSize))
        ) {
            Text(
                text = note.date,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(topStart = cornerSize, topEnd = cornerSize)
                    )
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center,
            )
            Row(verticalAlignment = Alignment.Top) {
                Column(modifier = Modifier.padding(bottom = 4.dp)) {
                    Text(
                        text = note.title,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 4.dp, bottom = 0.dp)
                            .fillMaxWidth(0.7f),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = note.text,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 0.dp, bottom = 4.dp)
                            .fillMaxWidth(0.7f),
                        textAlign = TextAlign.Start
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .height(80.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navigateToEditScreen(note.id) },
                        modifier = Modifier
                            .background(
                                color = EditButtonColor, shape = RoundedCornerShape(8.dp)
                            )
                            .size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit_icon),
                            contentDescription = "Edit icon"
                        )
                    }
                    IconButton(
                        onClick = { deleteNote(note) },
                        modifier = Modifier
                            .background(
                                color = DeleteButtonColor, shape = RoundedCornerShape(8.dp)
                            )
                            .size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.delete_icon),
                            contentDescription = "Delete icon",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun TextNoteItemPreview() {
    TextNoteItem(
        note = TextNoteState(
            id = 0,
            date = "12 Jan, 2024",
            title = "My note",
            text = "Preview preview preview preview preview preview preview preview preview ..."
        ),
        deleteNote = {},
        navigateToEditScreen = {}
    )
}