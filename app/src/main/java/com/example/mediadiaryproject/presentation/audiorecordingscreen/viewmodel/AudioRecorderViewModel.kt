package com.example.mediadiaryproject.presentation.audiorecordingscreen.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
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

    // States
    private val _state = mutableStateOf(AudioRecorderScreenState())
    val state: State<AudioRecorderScreenState> = _state

    private val _amplitudeState = mutableIntStateOf(0)
    val amplitudeState: State<Int> = _amplitudeState

    private var _amplitudesList = MutableList(25) { 0 }

    @SuppressLint("MutableCollectionMutableState")
    private val _amplitudesListState: MutableState<MutableList<Int>> =
        mutableStateOf(mutableListOf())

    val amplitudesListState = _amplitudesListState

    // Recorder
    private val recorder = MediaDiaryAudioRecorder(context)

    // Timers
    private var countDownTimerFast: CountDownTimer? = null

    private var countDownTimerSlow: CountDownTimer? = null

    @RequiresApi(Build.VERSION_CODES.S)
    fun startRecording() {
        val file = provideFileToSaveMediaUseCase.execute(mediaType = MediaType.AUDIO)

        _state.value = AudioRecorderScreenState(recording = true)

        recorder.start(file)

        assignListOfMaxAmpValue()
    }

    fun stopRecording() {
        recorder.stop()

        _state.value = AudioRecorderScreenState(recording = false, recordingSaved = true)

        countDownTimerFast?.cancel()
        countDownTimerSlow?.cancel()

    }

    private fun getListOfMaxAmpValue() = viewModelScope.launch {

        var intervalIndexCounter = 0

        countDownTimerFast = object : CountDownTimer(
            100,
            4
        ) {

            override fun onTick(millisUntilFinished: Long) {
                _amplitudesList[intervalIndexCounter] = recorder.getMaxAmpValue() ?: 0
                intervalIndexCounter++
            }

            override fun onFinish() {
                _amplitudesListState.value = _amplitudesList
                intervalIndexCounter = 0
                _amplitudesList = MutableList(25) { 0 }
            }
        }.start()

    }

    private fun assignListOfMaxAmpValue() = viewModelScope.launch {

        countDownTimerSlow = object : CountDownTimer(
            Constants.maxAudioLengthInMillis,
            Constants.audioAmplitudeDisplayIntervalInMillis
        ) {

            override fun onTick(millisUntilFinished: Long) {
                getListOfMaxAmpValue()
            }

            override fun onFinish() {
                stopRecording()
            }
        }.start()

    }

}