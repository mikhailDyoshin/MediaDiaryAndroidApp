package com.example.mediadiaryproject.presentation.audiosplayscreen.audioplayer

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)

    fun stop()
}