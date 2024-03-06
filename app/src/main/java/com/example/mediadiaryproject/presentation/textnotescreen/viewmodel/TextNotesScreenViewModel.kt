package com.example.mediadiaryproject.presentation.textnotescreen.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.usecase.DeleteTextNoteUseCase
import com.example.mediadiaryproject.domain.usecase.GetTextNotesByDayUseCase
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TextNotesScreenViewModel @Inject constructor(
    private val getTextNotesByDayUseCase: GetTextNotesByDayUseCase,
    private val deleteTextNoteUseCase: DeleteTextNoteUseCase,
) : ViewModel() {

    var state: MutableState<List<TextNoteState>> = mutableStateOf(listOf())
        private set

//    init {
//        getNotesByDate()
//    }

    fun getNotesByDay(dayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val notesList = getTextNotesByDayUseCase.execute(dayId = dayId).map { note ->
                TextNoteState(
                    id = note.id,
                    date = note.date,
                    title = note.title,
                    text = note.text
                )
            }
            state.value = notesList
        }

    }

    private fun getDateAndTime(): String {
        val time = Calendar.getInstance().time

        @SuppressLint("SimpleDateFormat")
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        return formatter.format(time)
    }

    fun deleteNote(note: TextNoteState, dayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val noteToDelete =
                TextNoteModel(
                    id = note.id,
                    dayId = dayId,
                    date = note.date,
                    title = note.title,
                    text = note.text
                )
            deleteTextNoteUseCase.execute(textNote = noteToDelete)
            getNotesByDay(dayId)
        }

    }

}