package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class GetTextNotesByDayUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(dayId: Int): List<TextNoteModel> {
        return repository.getTextNotesByDay(dayId)
    }
}