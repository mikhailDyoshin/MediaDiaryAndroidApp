package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class GetDaysByCollectionUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(collectionId: Int): List<DayModel> {
        return repository.getDaysByCollection(collectionId)
    }
}