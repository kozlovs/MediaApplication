package ru.kozlovss.mediaapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kozlovss.mediaapplication.dto.Album
import ru.kozlovss.mediaapplication.dto.Track

@Entity
data class AlbumEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val subtitle: String,
    val artist: String,
    val published: String,
    val genre: String,
    val tracks: List<Track>
) {
    companion object {
        fun fromDto(album: Album) = with(album) {
            AlbumEntity(id, title, subtitle, artist, published, genre, tracks)
        }
    }

    fun toDto() = Album(id, title, subtitle, artist, published, genre, tracks)
}