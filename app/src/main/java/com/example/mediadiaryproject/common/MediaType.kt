package com.example.mediadiaryproject.common

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi

enum class MediaType(val type: String, val directory: String) {
    VIDEO(type = "video", directory = Environment.DIRECTORY_DCIM),
    PHOTO(type = "photo", directory = Environment.DIRECTORY_DCIM),
    @RequiresApi(Build.VERSION_CODES.S)
    AUDIO(type = "audio", directory = Environment.DIRECTORY_RECORDINGS),
}