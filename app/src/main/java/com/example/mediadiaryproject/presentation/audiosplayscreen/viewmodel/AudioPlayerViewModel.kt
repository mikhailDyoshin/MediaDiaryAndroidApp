package com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
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

        player.addListener(
            object : Player.Listener {

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)

                    if (isPlaying) {
                        val duration = player.duration

                        val currentPosition = player.currentPosition

                        val timeRemained = duration - currentPosition

                        getCurrentAudioPosition(timeRemained)
                    } else {
                        countDownTimer?.cancel()
                    }
                }

            }
        )
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

        countDownTimer?.cancel()

        changeAudioPlayingStatus(audioItem.fileName)

        player.setMediaItem(audioItem.mediaItem)

        player.prepare()

        player.play()

    }

    fun seekTo(position: Float) {

        countDownTimer?.cancel()

        val duration = player.duration


        val currentSliderPosition = (position * duration).toLong()

        Log.d(
            "Slider Position",
            "$position; Current: $currentSliderPosition ms; Total: $duration; Remain: ${duration - currentSliderPosition}"
        )

        player.seekTo(currentSliderPosition)

    }

    private fun changeAudioPlayingStatus(audioName: String) {
        val newList = _state.value.map { audioItem ->
            audioItem.copy(isPlaying = audioItem.fileName == audioName)

        }

        _state.value = newList
    }

    private fun getCurrentAudioPosition(timeRemained: Long) = viewModelScope.launch {

        if (timeRemained >= 0) {
            var counter = 0

            countDownTimer = object : CountDownTimer(
                timeRemained + 200L,
                50L
            ) {

                override fun onTick(millisUntilFinished: Long) {
                    val currentRatio = (player.currentPosition.toFloat() / player.duration.toFloat())
                    _currentAudioPositionRatioState.floatValue = currentRatio
                    Log.d("Ticking", "$counter")
                    counter++
                }

                override fun onFinish() {
                    _currentAudioPositionRatioState.floatValue = 0f
                }
            }.start()
        }

    }

}