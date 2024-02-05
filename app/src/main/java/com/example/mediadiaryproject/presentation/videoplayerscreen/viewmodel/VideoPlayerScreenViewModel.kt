package com.example.mediadiaryproject.presentation.videoplayerscreen.viewmodel

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
class VideoPlayerScreenViewModel @Inject constructor(
    val player: Player,
) : ViewModel() {

    private val _state: MutableState<List<VideoFileState>> = mutableStateOf(listOf())
    val state = _state

    init {
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

    fun playVideo(videoId: Int) {

        val mediaItem = getVideoItem(videoId)

        player.addMediaItem(mediaItem)

        player.prepare()

        player.play()
    }

    private fun getVideoItem(videoId: Int): MediaItem {
        TODO()
    }

}