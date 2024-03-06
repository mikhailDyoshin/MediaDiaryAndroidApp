package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.models.MediaModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class GetMediaByIdUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    suspend fun execute(mediaId: Int): MediaModel =
        repository.getMediaById(mediaId = mediaId)
}