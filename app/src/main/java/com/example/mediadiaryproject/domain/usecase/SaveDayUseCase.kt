package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.CollectionModel
import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class SaveDayUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    suspend fun execute(dayToSave: DayModel, collection: CollectionModel) {
        repository.saveDay(day = dayToSave, collection = collection)
    }
}