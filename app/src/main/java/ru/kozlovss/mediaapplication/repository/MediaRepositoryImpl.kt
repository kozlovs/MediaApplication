package ru.kozlovss.mediaapplication.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.Dispatcher
import ru.kozlovss.mediaapplication.api.ApiModule.Companion.BASE_URL
import ru.kozlovss.mediaapplication.api.ApiService
import ru.kozlovss.mediaapplication.dao.AlbumDao
import ru.kozlovss.mediaapplication.db.AppDb
import ru.kozlovss.mediaapplication.dto.Album
import ru.kozlovss.mediaapplication.entity.AlbumEntity
import ru.kozlovss.mediaapplication.entity.toDto
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val apiService: ApiService,
    appDb: AppDb
) : MediaRepository {

    override val album: Flow<List<Album>> = albumDao
        .getAlbum()
        .map(List<AlbumEntity>::toDto)
        .flowOn(Dispatchers.Default)

    override fun getAll() {
        TODO("Not yet implemented")
    }

    companion object {
        fun getTrackUrl(trackUrl: String) = "${BASE_URL}"//todo переделать
    }
}