package com.example.mediadiaryproject.presentation.audiorecordingscreen.viewmodel

import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.common.Constants
import com.example.mediadiaryproject.common.Constants.NUMBER_OF_COLUMNS_IN_WAVEFORM
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.models.MediaModel
import com.example.mediadiaryproject.domain.usecase.ProvideFileToSaveMediaUseCase
import com.example.mediadiaryproject.presentation.audiorecordingscreen.audiorecorder.MediaDiaryAudioRecorder
import com.example.mediadiaryproject.presentation.audiorecordingscreen.state.AudioRecorderScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

@HiltViewModel
class AudioRecorderViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val provideFileToSaveMediaUseCase: ProvideFileToSaveMediaUseCase,
//    private val recorder: MediaDiaryAudioRecorder,
) : ViewModel() {

    // States
    private val _state = mutableStateOf(AudioRecorderScreenState())
    val state: State<AudioRecorderScreenState> = _state

    private val _amplitudesListState: MutableState<List<Int>> =
        mutableStateOf(mutableListOf())

    val amplitudesListState = _amplitudesListState

    // Recorder
    private val recorder = MediaDiaryAudioRecorder(context)

    // Timers
    private var countDownTimerFast: CountDownTimer? = null

    private var countDownTimerSlow: CountDownTimer? = null

    init {
        resetListOfAmplitudes()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun startRecording(dayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            resetListOfAmplitudes()

            val file = provideFileToSaveMediaUseCase.execute(
                media = MediaModel(
                    dayId = dayId,
                    mediaType = MediaType.AUDIO,
                    date = "",
                    time = "",
                    title = "Audio",
                    description = "",
                    pathToFile = ""
                )
            )

            _state.value = AudioRecorderScreenState(recording = true)

            recorder.start(file)

            assignListOfMaxAmpValues()
        }

    }

    fun stopRecording() {
        recorder.stop()

        _state.value = AudioRecorderScreenState(recording = false, recordingSaved = true)

        countDownTimerFast?.cancel()
        countDownTimerSlow?.cancel()

    }

    override fun onCleared() {
        super.onCleared()
        stopRecording()
    }

    private fun assignListOfMaxAmpValues() = viewModelScope.launch {

        countDownTimerSlow = object : CountDownTimer(
            Constants.maxAudioLengthInMillis,
            Constants.audioAmplitudeDisplayIntervalInMillis
        ) {

            override fun onTick(millisUntilFinished: Long) {
                val currentAmplitude = recorder.getMaxAmpValue() ?: 0
                _amplitudesListState.value = updateListOfAmplitudes(currentAmplitude)
//                    getGaussDistrib(currentAmplitude, Constants.NUMBER_OF_POINTS)
            }

            override fun onFinish() {
                stopRecording()
            }
        }.start()

    }

    private fun getGaussDistrib(amplitude: Int, numberOfPoints: Int): List<Double> {

        return List(numberOfPoints) { index ->
            getPointOfGaussDistrib(
                x = index,
                amplitude = amplitude
            )
        }
    }

    private fun getPointOfGaussDistrib(x: Int, amplitude: Int): Double {

        val powerNumerator = (x - Constants.EXPECTED_VAL).toFloat().pow(2)
        val powerDenominator = 50
        val power = -powerNumerator / powerDenominator

        return (amplitude * 5 / sqrt(2 * Constants.PI)) * Constants.EXP.toFloat().pow(power)
    }

    private fun updateListOfAmplitudes(amplitude: Int): List<Int> {
        val shiftedList = mutableListOf<Int>()

        // Copy elements from index 1 to the end
        shiftedList.addAll(_amplitudesListState.value.subList(1, _amplitudesListState.value.size))

        // Append the inputNumber to the end
        shiftedList.add(amplitude)
        return shiftedList
    }

    private fun resetListOfAmplitudes() {
        _amplitudesListState.value = List(NUMBER_OF_COLUMNS_IN_WAVEFORM) { 0 }
    }

}