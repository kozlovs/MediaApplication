package ru.kozlovss.mediaapplication.repository

import kotlinx.coroutines.flow.Flow
import ru.kozlovss.mediaapplication.dto.Album

interface MediaRepository {
    val album: Flow<List<Album>>

    fun getAll()
}