package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.CollectionModel
import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.models.TextNoteModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class DeleteDayUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(dayToDelete: DayModel, collection: CollectionModel) {
        repository.deleteDay(day = dayToDelete, collection = collection)
    }
}