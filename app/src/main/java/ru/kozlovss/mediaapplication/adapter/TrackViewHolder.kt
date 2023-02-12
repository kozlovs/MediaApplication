package ru.kozlovss.mediaapplication.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.kozlovss.mediaapplication.databinding.CardTrackBinding
import ru.kozlovss.mediaapplication.dto.Track
import ru.kozlovss.mediaapplication.ui.OnInteractionListener

class TrackViewHolder(
    private val binding: CardTrackBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(track: Track)  {
        binding.apply {
            trackName.text = track.file
            binding.playPauseButton.isChecked = onInteractionListener.isTrackSet(track) && onInteractionListener.isPlay()
        }
        setListeners(track)
    }

    private fun setListeners(track: Track) = with(binding) {
        playPauseButton.setOnClickListener {
            if (onInteractionListener.isPlay() && onInteractionListener.isTrackSet(track)) {
                onInteractionListener.onPause()
            } else {
                onInteractionListener.onPlay(track)
            }
        }
    }
}