package com.example.mediadiaryproject.presentation.audiorecordingscreen.audiorecorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)

    fun stop()
}