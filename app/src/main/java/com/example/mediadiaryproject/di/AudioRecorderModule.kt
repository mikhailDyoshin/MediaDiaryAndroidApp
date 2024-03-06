package com.example.mediadiaryproject.di

import android.app.Application
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.mediadiaryproject.presentation.audiorecordingscreen.audiorecorder.MediaDiaryAudioRecorder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AudioRecorderModule {

    @Provides
    @ViewModelScoped
    fun provideMediaDiaryAudioRecorder(
        @ApplicationContext context: Context
    ): MediaDiaryAudioRecorder {
        return MediaDiaryAudioRecorder(context = context)
    }

}