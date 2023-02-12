package ru.kozlovss.mediaapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.kozlovss.mediaapplication.databinding.CardTrackBinding
import ru.kozlovss.mediaapplication.dto.Track
import ru.kozlovss.mediaapplication.ui.OnInteractionListener

class TrackAdapter(private val onInteractionListener: OnInteractionListener) :
    ListAdapter<Track, TrackViewHolder>(TrackDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = CardTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        Log.d("MyLog", "onBindViewHolder")
        val track = getItem(position)
        holder.bind(track)
    }
}