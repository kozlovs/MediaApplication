package ru.kozlovss.mediaapplication.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TrackDao {

    fun getAlbum()

    fun getMusic()
}