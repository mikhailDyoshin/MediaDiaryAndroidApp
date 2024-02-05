package com.example.mediadiaryproject.presentation.videoslistscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.usecase.GetListOfMediaByDayAndTypeUseCase
import com.example.mediadiaryproject.presentation.videoplayerscreen.state.VideoFileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class VideosListScreenViewModel @Inject constructor(
    private val getListOfAllVideosUseCase: GetListOfMediaByDayAndTypeUseCase,
) : ViewModel() {

    private val _state: MutableState<List<VideoFileState>> = mutableStateOf(listOf())
    val state = _state

    fun getVideosList(dayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value =
                getListOfAllVideosUseCase.execute(mediaType = MediaType.VIDEO, dayId = dayId)
                    .map { videoFileModel ->
                        VideoFileState(
                            videoId = videoFileModel.id,
                            fileName = videoFileModel.title,
                            mediaItem = MediaItem.fromUri(videoFileModel.pathToFile.toUri())
                        )
                    }
        }

    }

}