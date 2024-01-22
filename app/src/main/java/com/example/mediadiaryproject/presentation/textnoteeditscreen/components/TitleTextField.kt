package com.example.mediadiaryproject.presentation.textnoteeditscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
fun TitleTextField(title: String, updateTitle: (title: String) -> Unit) {

    val maxLength = 50

    val blue = Color(0xff76a9ff)

    Column {

        Text(
            text = "Title",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            color = blue,
            textAlign = TextAlign.Start,
            fontFamily = meriendaFontFamily
        )
        TextField(
            value = title,
            onValueChange = { value -> if (value.length <= maxLength) updateTitle(value) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xffd8e6ff),
                cursorColor = Color.Black,
                disabledLabelColor = Color(0xffd8e6ff),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 22.sp, fontFamily = meriendaFontFamily),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                if (title.isNotEmpty()) {
                    IconButton(onClick = { updateTitle("") }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            }

        )

        Text(
            text = "${title.length} / $maxLength",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.End,
            color = blue
        )
    }
}

@Preview
@Composable
fun TitleTextFieldPreview() {
    TitleTextField(title = "Hello", updateTitle = {})
}
