package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.MediaModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import javax.inject.Inject

class UpdateMediaDataUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(mediaData: MediaModel) {
        repository.updateMediaData(mediaData = mediaData)
    }
}