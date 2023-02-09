package ru.kozlovss.mediaapplication.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kozlovss.mediaapplication.dto.Album
import ru.kozlovss.mediaapplication.dto.Track
import ru.kozlovss.mediaapplication.repository.MediaRepository
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    repository: MediaRepository
    ) : ViewModel() {

    private val _album = MutableStateFlow<Album?>(null)
    val album: StateFlow<Album?>
        get() = _album

    private val _executableTrack = MutableStateFlow<Track?>(null)
    val executableTrack: StateFlow<Track?>
        get() = _executableTrack

//    private val repository:

    fun playTrack(track: Track) {
        // MediaPlayer.create(context, R.raw.).start()
    }

    fun pauseTrack(track: Track) {

    }
}