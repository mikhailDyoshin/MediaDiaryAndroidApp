package com.example.mediadiaryproject.di

import android.content.Context
import com.example.mediadiaryproject.data.ComposeSandboxRepositoryImpl
import com.example.mediadiaryproject.domain.repository.ComposeSandboxRepository
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
    fun provideComposeSandboxRepository(@ApplicationContext context: Context): ComposeSandboxRepository {
        return  ComposeSandboxRepositoryImpl(context = context)
    }

}