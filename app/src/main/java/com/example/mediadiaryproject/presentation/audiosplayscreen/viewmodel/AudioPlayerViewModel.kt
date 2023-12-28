package com.example.mediadiaryproject.presentation.audiosplayscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(val player: Player,): ViewModel() {

    init {
        player.prepare()
    }
}