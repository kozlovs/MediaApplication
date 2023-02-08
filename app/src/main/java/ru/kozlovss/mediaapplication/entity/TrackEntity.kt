package ru.kozlovss.mediaapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kozlovss.mediaapplication.dto.Track

@Entity
data class TrackEntity(
    @PrimaryKey
    val id: Long,
    val file: String
) {
    companion object {
        fun fromDto(track: Track) = with(track) {
            TrackEntity(id, file)
        }
    }

    fun toDto() = Track(id, file)
}
