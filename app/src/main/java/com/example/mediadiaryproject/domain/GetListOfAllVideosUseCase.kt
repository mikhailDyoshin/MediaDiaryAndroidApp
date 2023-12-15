package com.example.mediadiaryproject.domain

import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import com.example.mediadiaryproject.domain.models.VideoFileModel
import javax.inject.Inject

class GetListOfAllVideosUseCase @Inject constructor(
    private val repository: MediaDiaryRepository
) {
    fun execute(): List<VideoFileModel> =
        repository.getListOfAllVideos()
}