package com.example.mediadiaryproject.presentation.textnotescreen.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TextNoteScreenViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableState<TextNoteScreenState> = mutableStateOf(TextNoteScreenState())
    val state = _state


    init {
        getDateAndTime()
    }

    private fun getDateAndTime() {
        val time = Calendar.getInstance().time

        @SuppressLint("SimpleDateFormat")
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        val date = formatter.format(time)

        _state.value = TextNoteScreenState(date = date)
    }

}