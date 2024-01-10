package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class GetTextNoteByIdUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(id: Int): TextNoteModel {
        return repository.getTextNoteById(id)
    }
}