package com.example.mediadiaryproject.presentation.daycontentscreen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediadiaryproject.presentation.carousel.Carousel
import com.example.mediadiaryproject.presentation.daycontentscreen.state.TextNoteCardState

@Composable
fun TextNotesCarousel(data: List<TextNoteCardState>) {
    Carousel(data) { dataUnit ->
        TextNoteCard(state = dataUnit)
    }
}

@Preview
@Composable
fun TextNoteCarouselPreview() {

    val data = listOf(
        TextNoteCardState(
            id = 0,
            title = "Weather is about to change",
            text = "Weather is very good"
        ),
        TextNoteCardState(
            id = 1,
            title = "Weather is about to change",
            text = "Weather is very good"
        ),
        TextNoteCardState(
            id = 2,
            title = "Weather is about to change",
            text = "Weather is very good"
        ),
    )

    TextNotesCarousel(data)
}