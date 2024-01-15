package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.CollectionModel
import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class GetCreatedDayUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    suspend fun execute(date: String, collection: CollectionModel): DayModel {
        return repository.getDayByCollectionAndDate(date = date, collection = collection)
    }
}