package ru.kozlovss.mediaapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kozlovss.mediaapplication.dto.Track

@Entity
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val trackName: String,
    val authorName: String,
    val albumName: String
) {
    companion object {
        fun fromDto(track: Track) = with(track) {
            TrackEntity(id, trackName, authorName, albumName)
        }
    }

    fun toDto() = Track(id, trackName, authorName, albumName)
}
