package com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.GetListOfMediaUseCase
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    private val getListOfAllAudiosUseCase: GetListOfMediaUseCase,
    private val player: Player,
) : ViewModel() {

    private val _state: MutableState<List<AudioFileState>> = mutableStateOf(listOf())
    val state = _state

    init {
        getVideosList()
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun getVideosList() {
        _state.value =
            getListOfAllAudiosUseCase.execute(mediaType = MediaType.AUDIO).map { videoFileModel ->
                AudioFileState(
                    fileName = videoFileModel.fileName,
                    mediaItem = MediaItem.fromUri(videoFileModel.filePath.toUri())
                )
            }
    }

    fun playAudio(audioItem: AudioFileState) {
        player.stop()

        changeAudioPlayingStatus(audioItem.fileName)

        player.setMediaItem(audioItem.mediaItem)

        player.prepare()

        player.play()

        player.currentPosition
    }

    fun seekTo(position: Long) {
        player.seekTo(position)
    }

    private fun changeAudioPlayingStatus(audioName: String) {
        val newList = _state.value.map { audioItem ->
            audioItem.copy(isPlaying = audioItem.fileName == audioName)

        }

        _state.value = newList
    }

}