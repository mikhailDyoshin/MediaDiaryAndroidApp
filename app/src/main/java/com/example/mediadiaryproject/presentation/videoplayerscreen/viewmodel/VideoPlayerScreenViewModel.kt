package com.example.mediadiaryproject.presentation.videoplayerscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.GetListOfMediaUseCase
import com.example.mediadiaryproject.presentation.videoplayerscreen.state.VideoFileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerScreenViewModel @Inject constructor(
    private val getListOfAllVideosUseCase: GetListOfMediaUseCase,
    val player: Player,
) : ViewModel() {

    private val _state: MutableState<List<VideoFileState>> = mutableStateOf(listOf())
    val state = _state

    init {
        getVideosList()
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

    private fun getVideosList() {
        _state.value = getListOfAllVideosUseCase.execute(mediaType = MediaType.VIDEO).map { videoFileModel ->
            VideoFileState(
                fileName = videoFileModel.fileName,
                mediaItem = MediaItem.fromUri(videoFileModel.filePath.toUri())
            )
        }
    }

    fun playVideo(mediaItem: MediaItem) {
        player.addMediaItem(mediaItem)

        player.prepare()

        player.play()
    }

}