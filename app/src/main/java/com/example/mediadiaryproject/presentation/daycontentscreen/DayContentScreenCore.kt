package com.example.mediadiaryproject.presentation.daycontentscreen

import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.daycontentscreen.components.TextNotesCarousel
import com.example.mediadiaryproject.presentation.daycontentscreen.state.TextNoteCardState

@Composable
fun DayContentScreenCore(state: List<TextNoteCardState>, openTextNote: (id: Int) -> Unit) {

    TextNotesCarousel(data = state, onFocusedItemClick = { openTextNote(it) })

}