package ru.kozlovss.mediaapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ru.kozlovss.mediaapplication.R
import ru.kozlovss.mediaapplication.adapter.TrackAdapter
import ru.kozlovss.mediaapplication.databinding.ActivityMainBinding
import ru.kozlovss.mediaapplication.dto.Track
import ru.kozlovss.mediaapplication.viewmodel.MediaViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel by viewModels <MediaViewModel>()
        val adapter = TrackAdapter(object : OnInteractionListener {
            override fun onPlay(track: Track) {
                viewModel.playTrack(track)
            }

            override fun onPause(track: Track) {
                viewModel.pauseTrack(track)
            }

        })
        binding.playList.adapter = adapter
        subscribe(binding, viewModel, adapter)


    }

    private fun subscribe(binding: ActivityMainBinding, viewModel: MediaViewModel, adapter: TrackAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.album.collect {
                with(binding) {
                    albumTitle.text = it?.title
                    albumSubtitle.text = it?.subtitle
                    artist.text = it?.artist
                    published.text = it?.published
                    genre.text = it?.genre
                }
                adapter.submitList(it?.tracks)
            }
        }
    }
}