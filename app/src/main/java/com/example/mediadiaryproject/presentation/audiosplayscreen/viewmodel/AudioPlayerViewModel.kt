package com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel

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
import com.example.mediadiaryproject.domain.usecase.GetListOfMediaUseCase
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

    private var savedCurrentPosition: Long = 0L

    private var currentAudioPlayed: MutableState<AudioFileState> = mutableStateOf(
        AudioFileState(fileName = "", mediaItem = MediaItem.EMPTY)
    )


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

        // If new audio is played reset the saved current position
        if (audioItem != currentAudioPlayed.value) {
            savedCurrentPosition = 0L
            _currentAudioPositionRatioState.floatValue = 0f
        }

        currentAudioPlayed.value = audioItem

        changeAudioUnderFocusStatus(currentAudioPlayed.value.fileName)

        player.setMediaItem(currentAudioPlayed.value.mediaItem)

        player.prepare()

        player.play()

        changeAudioPlayingStatus(
            audioName = currentAudioPlayed.value.fileName,
            isPlaying = true
        )

        player.seekTo(savedCurrentPosition)

    }

    fun pauseAudio() {
        player.pause()

        changeAudioPlayingStatus(currentAudioPlayed.value.fileName, false)

        savedCurrentPosition = player.currentPosition

        countDownTimer?.cancel()

    }

    fun seekTo(position: Float) {

        countDownTimer?.cancel()

        val duration = player.duration

        val currentSliderPosition = (position * duration).toLong()

        _currentAudioPositionRatioState.floatValue = position
        savedCurrentPosition = currentSliderPosition
        player.seekTo(currentSliderPosition)
        player.play()

        changeAudioPlayingStatus(
            audioName = currentAudioPlayed.value.fileName,
            isPlaying = true
        )

    }

    private fun changeAudioUnderFocusStatus(audioName: String) {
        val newList = _state.value.map { audioItem ->
            audioItem.copy(underFocus = audioItem.fileName == audioName)

        }

        _state.value = newList
    }

    private fun changeAudioPlayingStatus(audioName: String, isPlaying: Boolean) {
        val newList = _state.value.map { audioItem ->
            audioItem.copy(isPlaying = audioItem.fileName == audioName && isPlaying)

        }

        _state.value = newList
    }

    private fun getCurrentAudioPosition(timeRemained: Long) = viewModelScope.launch {

        if (timeRemained >= 0) {
            var counter = 0

            Log.d("Ticking", "Countdown is started ")

            countDownTimer = object : CountDownTimer(
                timeRemained + 200L,
                25L
            ) {

                override fun onTick(millisUntilFinished: Long) {
                    savedCurrentPosition = player.currentPosition
                    val currentRatio =
                        (player.currentPosition.toFloat() / player.duration.toFloat())
                    _currentAudioPositionRatioState.floatValue = currentRatio
                    Log.d("Ticking", "Tick: $counter, Ratio: $currentRatio")
                    counter++
                }

                override fun onFinish() {
                    _currentAudioPositionRatioState.floatValue = 0f
                    savedCurrentPosition = 0L
                    changeAudioPlayingStatus(
                        audioName = currentAudioPlayed.value.fileName,
                        isPlaying = false
                    )
                    Log.d("Ticking", "Tick: $counter, Finished")
                }
            }.start()
        }

    }

}