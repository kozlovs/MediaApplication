package ru.kozlovss.mediaapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.kozlovss.mediaapplication.adapter.TrackAdapter
import ru.kozlovss.mediaapplication.databinding.ActivityMainBinding
import ru.kozlovss.mediaapplication.dto.Track
import ru.kozlovss.mediaapplication.viewmodel.MediaViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MediaViewModel>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TrackAdapter(object : OnInteractionListener {
            override fun onPlay(track: Track) {
                viewModel.play(track)
            }

            override fun onPause() {
                viewModel.pause()
            }

            override fun isPlay() : Boolean {
                return viewModel.isPlaying.value == true
            }

            override fun isTrackSet(track: Track): Boolean {
                return viewModel.isTrackSet(track)
            }
        })
        binding.playList.adapter = adapter

        binding.playPauseButton.setOnClickListener {
            if (viewModel.isPlaying.value == true) {
                viewModel.pause()
            } else {
                viewModel.play()
            }
        }

        subscribe(adapter)
    }

    private fun subscribe(adapter: TrackAdapter) {
        viewModel.album.observe(this) {
            it?.let {
                binding.apply {
                    albumTitle.text = it.title
                    albumSubtitle.text = it.subtitle
                    artist.text = it.artist
                    published.text = it.published
                    genre.text = it.genre
                }
                it.tracks?.let { trackList ->
                    adapter.submitList(trackList)
                }
            }
        }

        viewModel.track.observe(this) {
            it?.let {
                binding.currentTrack.text = it.file
            }
        }

        viewModel.isPlaying.observe(this) { isPlaying ->
            binding.playPauseButton.isChecked = isPlaying
        }
    }
}