package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import java.io.File
import javax.inject.Inject

class ProvideFileToSaveMediaUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(mediaType: MediaType): File =
        repository.provideFileToSaveMedia(mediaType)
}