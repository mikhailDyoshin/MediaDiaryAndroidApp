package com.example.mediadiaryproject.presentation.photoview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
fun PhotoViewInfo(
    editMode: Boolean,
    title: String,
    description: String,
    updateTitle: (value: String) -> Unit,
    updateDescription: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {

    var offset by remember { mutableFloatStateOf(0f) }


    val textColor = Color.White

    val textDecoration = if (editMode) TextDecoration.Underline else TextDecoration.None

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
                .fillMaxWidth()
                .padding(bottom = 5.dp),
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
            cursorBrush = SolidColor(Color.White)
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
            cursorBrush = SolidColor(Color.White)
        )

    }

}

@Composable
private fun TitleDecorationBox(innerTextField: @Composable () -> Unit, editMode: Boolean) {

    val backgroundColor = if (editMode) {
        Color.Black
    } else {
        PhotoViewMenuBackground
    }

    Row(
        Modifier
            .background(backgroundColor, RoundedCornerShape(15.dp))
            .padding(top = 2.dp, bottom = 8.dp, start = 12.dp, end = 10.dp)
    ) {

        innerTextField()
    }


}

@Composable
private fun DescriptionDecorationBox(innerTextField: @Composable () -> Unit, editMode: Boolean) {

    val backgroundColor = if (editMode) {
        Color.Black
    } else {
        PhotoViewMenuBackground
    }

    Row(
        Modifier
            .background(backgroundColor, RoundedCornerShape(15.dp))
            .padding(top = 0.dp, bottom = 8.dp, start = 12.dp, end = 10.dp)
    ) {

        innerTextField()
    }

}

@Preview
@Composable
fun PhotoViewInfoPreview() {
    PhotoViewInfo(
        editMode = false,
        title = "My photo",
        description = "Some description",
        updateTitle = {},
        updateDescription = {}
    )
}

@Preview
@Composable
fun PhotoViewInfoEditModePreview() {
    PhotoViewInfo(
        editMode = true,
        title = "My photo",
        description = "Some description",
        updateTitle = {},
        updateDescription = {}
    )
}

@Preview
@Composable
fun PhotoViewInfoLotsOfTextPreview() {
    PhotoViewInfo(
        editMode = false,
        title = "My photo title very long long long long long long",
        description = "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Description",
        updateTitle = {},
        updateDescription = {}
    )
}
