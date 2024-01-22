package com.example.mediadiaryproject.presentation.textnoteeditscreen.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.ui.theme.meriendaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTextField(note: String, updateNote: (note: String) -> Unit, modifier: Modifier) {

    var offset by remember { mutableFloatStateOf(0f) }

    val blue = Color(0xff76a9ff)

    Column(modifier = modifier) {

        Text(
            text = "Note",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            color = blue,
            textAlign = TextAlign.Start,
            fontFamily = meriendaFontFamily
        )
        TextField(
            value = note,
            onValueChange = { value -> updateNote(value) },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .scrollable(
                orientation = Orientation.Vertical,
                // Scrollable state: describes how to consume
                // scrolling delta and update offset
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xffd8e6ff),
                cursorColor = Color.Black,
                disabledLabelColor = Color(0xffd8e6ff),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 22.sp, fontFamily = meriendaFontFamily),
            shape = RoundedCornerShape(8.dp),
            singleLine = false,
        )
    }
}

@Preview
@Composable
fun NoteTextFieldPreview() {

    val note =
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted" +
        "Whereas disregard and contempt for human rights have resulted"

    NoteTextField(
        note = note,
        updateNote = {},
        modifier = Modifier
    )
}
