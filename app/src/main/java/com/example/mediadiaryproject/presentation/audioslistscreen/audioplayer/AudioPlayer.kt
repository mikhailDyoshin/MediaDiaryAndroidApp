package com.example.mediadiaryproject.presentation.audioslistscreen.audioplayer

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)

    fun stop()
}