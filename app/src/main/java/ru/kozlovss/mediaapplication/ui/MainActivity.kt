package ru.kozlovss.mediaapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kozlovss.mediaapplication.R
import ru.kozlovss.mediaapplication.adapter.TrackAdapter
import ru.kozlovss.mediaapplication.databinding.ActivityMainBinding
import ru.kozlovss.mediaapplication.dto.Track

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.playList.adapter = TrackAdapter(object : OnInteractionListener {
            override fun onPlay(track: Track) {
                TODO("Not yet implemented")
            }

            override fun onPause(track: Track) {
                TODO("Not yet implemented")
            }

        })
        setContentView(R.layout.activity_main)
    }
}