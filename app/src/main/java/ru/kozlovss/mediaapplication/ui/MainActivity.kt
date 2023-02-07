package ru.kozlovss.mediaapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ru.kozlovss.mediaapplication.R
import ru.kozlovss.mediaapplication.adapter.TrackAdapter
import ru.kozlovss.mediaapplication.databinding.ActivityMainBinding
import ru.kozlovss.mediaapplication.dto.Track
import ru.kozlovss.mediaapplication.viewmodel.TracksViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel by viewModels <TracksViewModel>()
        binding.playList.adapter = TrackAdapter(object : OnInteractionListener {
            override fun onPlay(track: Track) {
                viewModel.playTrack(track)
            }

            override fun onPause(track: Track) {
                viewModel.pauseTrack(track)
            }

        })

        subscribe(binding, viewModel)


    }

    private fun subscribe(binding: ActivityMainBinding, viewModel: TracksViewModel) {
        lifecycleScope.launchWhenCreated {
            viewModel.executableTrack.collect {
                with(binding) {
                }
            }
        }
    }
}