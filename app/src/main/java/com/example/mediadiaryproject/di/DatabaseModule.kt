package com.example.mediadiaryproject.di

import android.content.Context
import androidx.room.Room
import com.example.mediadiaryproject.data.storage.dao.DayDao
import com.example.mediadiaryproject.data.storage.dao.TextNoteDao
import com.example.mediadiaryproject.data.storage.database.MediaDiaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMediaDiaryDatabase(@ApplicationContext context: Context): MediaDiaryDatabase =
        Room.databaseBuilder(
            context,
            MediaDiaryDatabase::class.java,
            "MediaDiaryDatabase",
        ).build()

    @Provides
    fun provideTextNoteDao(userDatabase: MediaDiaryDatabase):TextNoteDao = userDatabase.textNoteDao()

    @Provides
    fun provideDayDao(userDatabase: MediaDiaryDatabase):DayDao = userDatabase.dayDao()

}