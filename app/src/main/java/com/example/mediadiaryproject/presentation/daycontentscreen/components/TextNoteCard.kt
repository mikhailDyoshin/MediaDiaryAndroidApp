package com.example.mediadiaryproject.presentation.daycontentscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.presentation.daycontentscreen.state.TextNoteCardState

@Composable
fun TextNoteCard(state: TextNoteCardState) {

    val cornerSize = 10.dp


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .heightIn(min = 80.dp, max = 100.dp)
            .width(200.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 5.dp)) {
                Text(
                    text = state.title,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 4.dp, bottom = 0.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = state.text,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 0.dp, bottom = 4.dp),
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}


@Preview
@Composable
fun TextNoteCardPreview() {

    val state = TextNoteCardState(
        id = 0,
        title = "Weather is about to change",
        text = "Weather is very very very very very very very very very very very very very very good)))"
    )

    TextNoteCard(state = state)
}