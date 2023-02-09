package ru.kozlovss.mediaapplication.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.kozlovss.mediaapplication.dto.Album
import ru.kozlovss.mediaapplication.entity.AlbumEntity

@Dao
interface AlbumDao {

    @Query("SELECT * FROM AlbumEntity ORDER BY id DESC")
    fun getAlbum(): Flow<List<AlbumEntity>>
}