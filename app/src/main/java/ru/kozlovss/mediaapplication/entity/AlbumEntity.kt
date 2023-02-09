package ru.kozlovss.mediaapplication.entity

import androidx.room.Embedded
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
//    @Embedded
//    val tracks: List<Track>? = null
) {
    companion object {
        fun fromDto(album: Album) = with(album) {
            AlbumEntity(id, title, subtitle, artist, published, genre)
        }
    }

    fun toDto() = Album(id, title, subtitle, artist, published, genre)
}

fun List<AlbumEntity>.toDto() = map(AlbumEntity::toDto)

fun List<Album>.toEntity() = map(AlbumEntity::fromDto)