package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.DayModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class GetDayByIdUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(dayId: Int): DayModel {
        return repository.getDayById(dayId)
    }
}