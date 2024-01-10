package com.example.mediadiaryproject.presentation.textnoteeditscreen.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.usecase.SaveTextNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TextNoteEditScreenViewModel @Inject constructor(
    private val saveTextNoteUseCase: SaveTextNoteUseCase
) : ViewModel() {
    var date by mutableStateOf("")
        private set

    var title by mutableStateOf("")
        private set

    var text by mutableStateOf("")
        private set

    init {
        getDateAndTime()
    }

//    override fun onCleared() {
//        super.onCleared()
//
//    }

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

    fun saveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val textNoteToSave = TextNoteModel(date = date, title = title, text = text)
            saveTextNoteUseCase.execute(textNote = textNoteToSave)
        }
    }

}