package ru.kozlovss.mediaapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kozlovss.mediaapplication.dto.Track

class TracksViewModel(context: Application) : AndroidViewModel(context) {

    private val _executableTrack = MutableStateFlow<Track?>(null)
    val executableTrack: StateFlow<Track?>
        get() = _executableTrack

//    private val repository:

    fun playTrack(track: Track) {

    }

    fun pauseTrack(track: Track) {

    }
}