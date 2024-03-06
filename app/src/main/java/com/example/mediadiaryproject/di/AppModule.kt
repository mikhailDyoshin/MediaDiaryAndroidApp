package com.example.mediadiaryproject.di

import android.content.Context
import com.example.mediadiaryproject.data.MediaDiaryRepositoryImpl
import com.example.mediadiaryproject.data.storage.dao.DayDao
import com.example.mediadiaryproject.data.storage.dao.MediaDao
import com.example.mediadiaryproject.data.storage.dao.TextNoteDao
import com.example.mediadiaryproject.domain.usecase.GetListOfMediaByDayAndTypeUseCase
import com.example.mediadiaryproject.domain.usecase.ProvideFileToSaveMediaUseCase
import com.example.mediadiaryproject.domain.usecase.SavePhotoToGalleryUseCase
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
import com.example.mediadiaryproject.domain.usecase.SaveTextNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideComposeSandboxRepository(
        @ApplicationContext context: Context,
        textNoteDao: TextNoteDao,
        dayDao: DayDao,
        mediaDao: MediaDao,
    ): MediaDiaryRepository {
        return MediaDiaryRepositoryImpl(
            context = context,
            textNoteDao = textNoteDao,
            dayDao = dayDao,
            mediaDao = mediaDao,
        )
    }

    @Provides
    fun provideSavePhotoToGalleryUseCase(
        repository: MediaDiaryRepository
    ): SavePhotoToGalleryUseCase {
        return SavePhotoToGalleryUseCase(repository = repository)
    }

    @Provides
    fun provideProvideFileToSaveVideoUseCase(
        repository: MediaDiaryRepository
    ): ProvideFileToSaveMediaUseCase {
        return ProvideFileToSaveMediaUseCase(repository = repository)
    }

    @Provides
    fun provideGetListOfMediaUseCase(
        repository: MediaDiaryRepository
    ): GetListOfMediaByDayAndTypeUseCase {
        return GetListOfMediaByDayAndTypeUseCase(repository = repository)
    }

    @Provides
    fun provideSaveTextNoteUseCase(
        repository: MediaDiaryRepository
    ): SaveTextNoteUseCase {
        return SaveTextNoteUseCase(repository = repository)
    }

}