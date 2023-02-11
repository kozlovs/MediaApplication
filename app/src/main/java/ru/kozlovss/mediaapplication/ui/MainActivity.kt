package ru.kozlovss.mediaapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                viewModel.playTrack(track)
            }

            override fun onPause(track: Track) {
                viewModel.pauseTrack(track)
            }
        })
        binding.playList.adapter = adapter
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
    }
}