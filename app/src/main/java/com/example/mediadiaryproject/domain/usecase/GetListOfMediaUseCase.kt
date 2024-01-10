package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import com.example.mediadiaryproject.domain.models.MediaFileModel
import javax.inject.Inject

class GetListOfMediaUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(mediaType: MediaType): List<MediaFileModel> =
        repository.getListOfMedia(mediaType)
}