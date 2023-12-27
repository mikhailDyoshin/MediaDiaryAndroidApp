package com.example.mediadiaryproject.presentation.audiorecordingscreen.viewmodel

import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.common.Constants
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.ProvideFileToSaveMediaUseCase
import com.example.mediadiaryproject.presentation.audiorecordingscreen.audiorecorder.MediaDiaryAudioRecorder
import com.example.mediadiaryproject.presentation.audiorecordingscreen.state.AudioRecorderScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioRecorderViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val provideFileToSaveMediaUseCase: ProvideFileToSaveMediaUseCase,
//    private val recorder: MediaDiaryAudioRecorder,
) : ViewModel() {

    private val _state = mutableStateOf(AudioRecorderScreenState())
    val state: State<AudioRecorderScreenState> = _state

    private val _amplitudeState = mutableIntStateOf(0)
    val amplitudeState: State<Int> = _amplitudeState

    private val recorder = MediaDiaryAudioRecorder(context)

    private var countDownTimer: CountDownTimer? = null

    @RequiresApi(Build.VERSION_CODES.S)
    fun startRecording() {
        val file = provideFileToSaveMediaUseCase.execute(mediaType = MediaType.AUDIO)

        _state.value = AudioRecorderScreenState(recording = true)

        recorder.start(file)

        getMaxAmpValue()
    }

    fun stopRecording() {
        recorder.stop()

        _state.value = AudioRecorderScreenState(recording = false, recordingSaved = true)

        countDownTimer?.cancel()

    }

    private fun getMaxAmpValue() = viewModelScope.launch {

        countDownTimer = object : CountDownTimer(
            Constants.maxAudioLengthInMillis,
            Constants.audioAmplitudeDisplayIntervalInMillis
        ) {

            override fun onTick(millisUntilFinished: Long) {
                _amplitudeState.intValue = recorder.getMaxAmpValue() ?: 0
            }

            override fun onFinish() {
                _amplitudeState.intValue = 0
                stopRecording()
            }
        }.start()

    }

}