package ru.kozlovss.mediaapplication.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.kozlovss.mediaapplication.dto.Track

class TrackDiffCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Track, newItem: Track) = oldItem == newItem
}