package com.example.mediadiaryproject.presentation.textnoteeditscreen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.usecase.GetTextNoteByIdUseCase
import com.example.mediadiaryproject.domain.usecase.SaveTextNoteUseCase
import com.example.mediadiaryproject.domain.usecase.UpdateTextNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextNoteEditScreenViewModel @Inject constructor(
    private val saveTextNoteUseCase: SaveTextNoteUseCase,
    private val getTextNoteByIdUseCase: GetTextNoteByIdUseCase,
    private val updateTextNoteUseCase: UpdateTextNoteUseCase,
) : ViewModel() {
//    var date by mutableStateOf("")
//        private set

    var title by mutableStateOf("")
        private set

    var text by mutableStateOf("")
        private set

    var dayId by mutableIntStateOf(0)
        private set

    var editedTextNoteDate by mutableStateOf("")
        private set

//    init {
//        getDate()
//    }

//    override fun onCleared() {
//        super.onCleared()
//
//    }

//    private fun getDate() {
//        val time = Calendar.getInstance().time
//
//        @SuppressLint("SimpleDateFormat")
//        val formatter = SimpleDateFormat("yyyy-MM-dd")
//
//        date = formatter.format(time)
//    }

    fun updateTitle(input: String) {
        title = input
    }

    fun updateText(input: String) {
        text = input
    }

    fun updateDayId(id: Int) {
        dayId = id
    }

    fun saveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val textNoteToSave =
                TextNoteModel(dayId = dayId, date = editedTextNoteDate, title = title, text = text)
            saveTextNoteUseCase.execute(textNote = textNoteToSave)
        }
    }

    fun setNoteData(textNoteToEditId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = getTextNoteByIdUseCase.execute(id = textNoteToEditId)
            editedTextNoteDate = note.date
            updateTitle(input = note.title)
            updateText(input = note.text)
            updateDayId(id = note.dayId)
        }
    }

    fun updateNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val noteToUpdate =
                TextNoteModel(
                    id = id,
                    dayId = dayId,
                    date = editedTextNoteDate,
                    title = title,
                    text = text
                )
            updateTextNoteUseCase.execute(textNote = noteToUpdate)
        }
    }

}