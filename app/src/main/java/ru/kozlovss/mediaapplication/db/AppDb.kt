package ru.kozlovss.mediaapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kozlovss.mediaapplication.dao.AlbumDao
import ru.kozlovss.mediaapplication.entity.AlbumEntity

@Database(
    entities = [AlbumEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}