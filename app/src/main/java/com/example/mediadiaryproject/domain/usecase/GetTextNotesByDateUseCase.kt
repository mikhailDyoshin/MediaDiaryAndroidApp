package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class GetTextNotesByDateUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(date: String) {
        repository.getTextNotesWithDate(date)
    }
}