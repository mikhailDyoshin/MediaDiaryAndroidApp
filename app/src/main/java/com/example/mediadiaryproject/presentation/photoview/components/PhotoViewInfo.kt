package com.example.mediadiaryproject.presentation.photoview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.ui.theme.HalfTransparent
import com.example.mediadiaryproject.ui.theme.PhotoViewMenuBackground

@Composable
fun PhotoViewInfo(title: String, description: String, modifier: Modifier = Modifier) {

    var offset by remember { mutableFloatStateOf(0f) }


    val textColor = Color.White

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = PhotoViewMenuBackground)
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        Text(
            text = title,
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = description,
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier.scrollable(
                orientation = Orientation.Vertical,
                // Scrollable state: describes how to consume
                // scrolling delta and update offset
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                })
        )
    }

}

@Preview
@Composable
fun PhotoViewInfoPreview() {
    PhotoViewInfo(title = "My photo", description = "Some description")
}

@Preview
@Composable
fun PhotoViewInfoLotsOfTextPreview() {
    PhotoViewInfo(
        title = "My photo title very long long long long long long",
        description = "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Long\n" +
                "Description"
    )
}
