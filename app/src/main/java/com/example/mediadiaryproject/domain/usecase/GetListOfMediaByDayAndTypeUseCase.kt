package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import com.example.mediadiaryproject.domain.models.MediaModel
import javax.inject.Inject

class GetListOfMediaByDayAndTypeUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    suspend fun execute(dayId: Int, mediaType: MediaType): List<MediaModel> =
        repository.getMediaByDayAndType(dayId = dayId, mediaType = mediaType)
}