package ru.kozlovss.mediaapplication.repository

import ru.kozlovss.mediaapplication.dto.Album

interface MediaRepository {
    fun getTrackUrl(trackName: String): String
    abstract fun getAlbumAsync(callback: Callback<Album>)

    interface Callback<T> {
        fun onSuccess(result: T)
        fun onError(e: Exception)
    }
}