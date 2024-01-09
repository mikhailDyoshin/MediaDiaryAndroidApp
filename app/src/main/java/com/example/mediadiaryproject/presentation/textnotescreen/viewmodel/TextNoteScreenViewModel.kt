package com.example.mediadiaryproject.presentation.textnotescreen.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TextNoteScreenViewModel @Inject constructor() : ViewModel() {
    var date by mutableStateOf("")
        private set

    var title by mutableStateOf("")
        private set

    var text by mutableStateOf("")
        private set

    init {
        getDateAndTime()
    }

    private fun getDateAndTime() {
        val time = Calendar.getInstance().time

        @SuppressLint("SimpleDateFormat")
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        date = formatter.format(time)
    }

    fun updateTitle(input: String) {
        title = input
    }

    fun updateText(input: String) {
        text = input
    }

}