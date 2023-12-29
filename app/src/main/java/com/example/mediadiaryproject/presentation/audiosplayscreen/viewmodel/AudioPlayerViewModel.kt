package com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel

import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.GetListOfMediaUseCase
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    private val getListOfAllAudiosUseCase: GetListOfMediaUseCase,
    private val player: Player,
) : ViewModel() {

    private val _state: MutableState<List<AudioFileState>> = mutableStateOf(listOf())
    val state = _state

    private val _currentAudioPositionRatioState: MutableFloatState = mutableFloatStateOf(0f)

    val currentAudioPositionState = _currentAudioPositionRatioState

    private var countDownTimer: CountDownTimer? = null

    init {
        getVideosList()
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
        countDownTimer?.cancel()
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

//        countDownTimer?.cancel()

        changeAudioPlayingStatus(audioItem.fileName)

        player.setMediaItem(audioItem.mediaItem)

        player.prepare()

        player.addListener(
            object: Player.Listener {

                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    if (events.contains(Player.EVENT_IS_LOADING_CHANGED)) {
                        val duration = player.duration

                        getCurrentAudioPosition(duration)
                    }

//                    if (events.contains(Player.EVENT_PLAYBACK_STATE_CHANGED)) {
//                        countDownTimer?.cancel()
//                    }
                }



            }
        )

        player.play()

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

    private fun getCurrentAudioPosition(duration: Long) = viewModelScope.launch {



        countDownTimer = object : CountDownTimer(
            duration+100,
            1
        ) {

            override fun onTick(millisUntilFinished: Long) {
                val currentRatio = (player.currentPosition.toFloat() / duration.toFloat())
                _currentAudioPositionRatioState.floatValue = currentRatio
            }

            override fun onFinish() {
                _currentAudioPositionRatioState.floatValue = 0f
            }
        }.start()

    }

}