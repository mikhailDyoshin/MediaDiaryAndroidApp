package com.example.mediadiaryproject.di

import android.content.Context
import com.example.mediadiaryproject.data.MediaDiaryRepositoryImpl
import com.example.mediadiaryproject.domain.ProvideFileToSaveVideoUseCase
import com.example.mediadiaryproject.domain.SavePhotoToGalleryUseCase
import com.example.mediadiaryproject.domain.repository.MediaDiaryRepository
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
    fun provideComposeSandboxRepository(@ApplicationContext context: Context): MediaDiaryRepository {
        return MediaDiaryRepositoryImpl(context = context)
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
    ): ProvideFileToSaveVideoUseCase {
        return ProvideFileToSaveVideoUseCase(repository = repository)
    }

}