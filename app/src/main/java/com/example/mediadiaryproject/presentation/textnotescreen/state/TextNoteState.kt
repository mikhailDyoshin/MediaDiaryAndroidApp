package com.example.mediadiaryproject.presentation.textnotescreen.state

data class TextNoteState(
    var id: Int = 0,
    var date: String,
    val title: String,
    val text: String
)
