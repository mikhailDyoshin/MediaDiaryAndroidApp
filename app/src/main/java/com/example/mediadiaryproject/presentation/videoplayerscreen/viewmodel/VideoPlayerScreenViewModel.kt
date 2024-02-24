package com.example.mediadiaryproject.presentation.videoplayerscreen.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.mediadiaryproject.domain.usecase.GetMediaByIdUseCase
import com.example.mediadiaryproject.presentation.videoplayerscreen.state.VideoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerScreenViewModel @Inject constructor(
    val player: Player,
    private val getVideoUseCase: GetMediaByIdUseCase
) : ViewModel() {

    private val _state: MutableState<VideoState?> = mutableStateOf(null)
    val state = _state

    private val _menuState: MutableState<Boolean> = mutableStateOf(true)
    val menuState = _menuState

    init {
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

    fun toggleMenu() {
        _menuState.value = !_menuState.value
    }

    fun playVideo() {

        val mediaItem = _state.value?.mediaItem

        if (mediaItem != null) {
            player.addMediaItem(mediaItem)

            player.prepare()

            player.play()
        }

    }

    fun getVideoItem(videoId: Int): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            val videoFileModel = getVideoUseCase.execute(mediaId = videoId)
            _state.value = VideoState(
                id = videoFileModel.id,
                title = videoFileModel.title,
                description = videoFileModel.description,
                mediaItem = MediaItem.fromUri(videoFileModel.pathToFile.toUri()),
                date = videoFileModel.date,
                time = videoFileModel.time,
            )
        }
    }

}