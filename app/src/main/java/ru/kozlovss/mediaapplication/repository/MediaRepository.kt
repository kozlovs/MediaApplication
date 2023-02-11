package ru.kozlovss.mediaapplication.repository

import ru.kozlovss.mediaapplication.dto.Album

interface MediaRepository {
    suspend fun getTrack()
    abstract fun getAlbumAsync(callback: Callback<Album>)

    interface Callback<T> {
        fun onSuccess(result: T)
        fun onError(e: Exception)
    }
}