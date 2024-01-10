package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class UpdateTextNoteUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(textNote: TextNoteModel) {
        repository.updateTextNote(textNote = textNote)
    }
}