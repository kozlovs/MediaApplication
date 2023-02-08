package ru.kozlovss.mediaapplication.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kozlovss.mediaapplication.repository.MediaRepository
import ru.kozlovss.mediaapplication.repository.MediaRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsMediaRepository(impl: MediaRepositoryImpl): MediaRepository
}