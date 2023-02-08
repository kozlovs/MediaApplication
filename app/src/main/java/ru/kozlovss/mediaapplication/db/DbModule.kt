package ru.kozlovss.mediaapplication.db

import android.content.Context
import androidx.room.Room
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kozlovss.mediaapplication.dao.AlbumDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
class DbModule {

    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext
        context: Context
    ): AppDb = Room.databaseBuilder(context, AppDb::class.java, "app.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideAlbumDao(
        appDb: AppDb
    ): AlbumDao = appDb.albumDao()
}