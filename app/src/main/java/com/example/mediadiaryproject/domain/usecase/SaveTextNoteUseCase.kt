package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class SaveTextNoteUseCase @Inject constructor(
private val repository: MediaDiaryRepository
) {
    suspend fun execute(textNote: TextNoteModel) {
        repository.saveTextNote(textNote = textNote)
    }
}
