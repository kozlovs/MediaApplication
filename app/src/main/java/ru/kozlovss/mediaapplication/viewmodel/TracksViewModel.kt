package ru.kozlovss.mediaapplication.viewmodel

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kozlovss.mediaapplication.R
import ru.kozlovss.mediaapplication.dto.Track

class TracksViewModel(private val context: Application) : AndroidViewModel(context) {

    private val _executableTrack = MutableStateFlow<Track?>(null)
    val executableTrack: StateFlow<Track?>
        get() = _executableTrack

//    private val repository:

    fun playTrack(track: Track) {
        MediaPlayer.create(context, R.raw.).start()
    }

    fun pauseTrack(track: Track) {

    }
}