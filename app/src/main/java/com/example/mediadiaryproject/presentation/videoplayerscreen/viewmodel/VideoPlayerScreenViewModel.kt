package com.example.mediadiaryproject.presentation.videoplayerscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediadiaryproject.domain.GetListOfAllVideosUseCase
import com.example.mediadiaryproject.presentation.videoplayerscreen.state.VideoFileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerScreenViewModel @Inject constructor(
    private val getListOfAllVideosUseCase: GetListOfAllVideosUseCase
) : ViewModel() {

    private val _state: MutableState<List<VideoFileState>> = mutableStateOf(listOf())
    val state = _state

    init {
        getVideosList()
    }

    private fun getVideosList() {
        _state.value = getListOfAllVideosUseCase.execute().map { videoFileModel ->
            VideoFileState(
                fileName = videoFileModel.fileName,
                filePath = videoFileModel.filePath
            )
        }
    }

}