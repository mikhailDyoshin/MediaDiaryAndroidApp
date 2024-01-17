package com.example.mediadiaryproject.domain.usecase

import com.example.mediadiaryproject.domain.models.MediaModel
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import java.io.File
import javax.inject.Inject

class ProvideFileToSaveMediaUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    suspend fun execute(media: MediaModel): File =
        repository.provideFileToSaveMedia(media)
}