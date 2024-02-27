package com.example.mediadiaryproject.presentation.audioplayerscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.ui.theme.PhotoViewMenuBackground
import com.example.mediadiaryproject.ui.theme.meriendaFontFamily

@Composable
fun AudioInfo(
    editMode: Boolean,
    title: String,
    description: String,
    updateTitle: (value: String) -> Unit,
    updateDescription: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var offset by remember { mutableFloatStateOf(0f) }


    val textColor = if (editMode) Color.White else Color.Black

    val cursorColor = if (editMode) Color.White else Color.Black

    val textDecoration = TextDecoration.None

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = PhotoViewMenuBackground)
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        // Title
        BasicTextField(
            value = title,
            onValueChange = { value -> updateTitle(value) },
            enabled = editMode,
            readOnly = !editMode,
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(
                color = textColor,
                fontSize = 24.sp,
                fontFamily = meriendaFontFamily,
                textDecoration = textDecoration
            ),
            decorationBox = { innerTextField ->
                TitleDecorationBox(innerTextField, editMode)
            },
            cursorBrush = SolidColor(cursorColor)
        )

        // Description
        BasicTextField(
            value = description,
            onValueChange = { value -> updateDescription(value) },
            enabled = editMode,
            readOnly = !editMode,
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
                    }),
            singleLine = false,
            textStyle = TextStyle(
                color = textColor,
                fontSize = 16.sp,
                fontFamily = meriendaFontFamily,
                textDecoration = textDecoration
            ),
            decorationBox = { innerTextField ->
                DescriptionDecorationBox(innerTextField, editMode)
            },
            cursorBrush = SolidColor(cursorColor)
        )

    }
}

@Composable
private fun TitleDecorationBox(innerTextField: @Composable () -> Unit, editMode: Boolean) {

    val backgroundColor = if (editMode) {
        Color.Gray
    } else {
        Color.White
    }

    Row(
        Modifier
            .background(
                backgroundColor,
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .padding(top = 2.dp, bottom = 2.dp, start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.Start
    ) {

        innerTextField()
    }


}

@Composable
private fun DescriptionDecorationBox(innerTextField: @Composable () -> Unit, editMode: Boolean) {

    val backgroundColor = if (editMode) {
        Color.Gray
    } else {
        Color.White
    }

    Row(
        Modifier
            .background(
                backgroundColor,
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 15.dp,
                    bottomEnd = 15.dp
                )
            )
            .padding(top = 0.dp, bottom = 8.dp, start = 12.dp, end = 12.dp)
    ) {

        innerTextField()
    }

}

@Preview
@Composable
fun AudioInfoPreview() {
    AudioInfo(
        editMode = false,
        title = "My audio",
        description = "Some description",
        updateTitle = {},
        updateDescription = {}
    )
}

@Preview
@Composable
fun AudioInfoEditModePreview() {
    AudioInfo(
        editMode = true,
        title = "My audio",
        description = "Some description",
        updateTitle = {},
        updateDescription = {}
    )
}
