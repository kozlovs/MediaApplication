package ru.kozlovss.mediaapplication.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.kozlovss.mediaapplication.dto.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM AlbumEntity ORDER BY id DESC")
    fun getAlbum(): Flow<List<Album>>

    fun getMusic()
}