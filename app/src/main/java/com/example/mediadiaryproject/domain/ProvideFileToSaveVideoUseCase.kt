package com.example.mediadiaryproject.domain

import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import java.io.File
import javax.inject.Inject

class ProvideFileToSaveVideoUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(): File =
        repository.provideFileToSaveVideo()
}