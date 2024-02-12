package com.example.mediadiaryproject.presentation.daycontentscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.domain.usecase.DeleteTextNoteUseCase
import com.example.mediadiaryproject.domain.usecase.GetTextNotesByDayUseCase
import com.example.mediadiaryproject.presentation.daycontentscreen.state.TextNoteCardState
import com.example.mediadiaryproject.presentation.textnotescreen.state.TextNoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayContentViewModel @Inject constructor(
    private val getTextNotesByDayUseCase: GetTextNotesByDayUseCase,
    private val deleteTextNoteUseCase: DeleteTextNoteUseCase,
) : ViewModel() {

    var state: MutableState<List<TextNoteCardState>> = mutableStateOf(listOf())
        private set

    fun getNotesByDay(dayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val notesList = getTextNotesByDayUseCase.execute(dayId = dayId).map { note ->
                TextNoteCardState(
                    id = note.id,
                    title = note.title,
                    text = note.text
                )
            }
            state.value = notesList
        }

    }

}