package com.example.mediadiaryproject.presentation.audiorecordingscreen.audiorecorder

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

class MediaDiaryAudioRecorder(private val context: Context): AudioRecorder {

    private var recorder: MediaRecorder? = null

    private var showAmplitude = false

    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }
    override fun start(outputFile: File) {
        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)

            prepare()
            start()

            recorder = this

            showAmplitude = true
        }
    }

    override fun stop() {
        recorder?.stop()
        recorder?.reset()
        recorder = null

        showAmplitude = false
    }

    fun getMaxAmpValue(): Int? {
        return recorder?.maxAmplitude
    }
}
