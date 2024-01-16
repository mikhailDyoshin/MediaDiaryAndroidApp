package com.example.mediadiaryproject.domain.models

data class TextNoteModel(
    var id: Int = 0,
    val dayId: Int,
    var date: String,
    val title: String,
    val text: String
)
