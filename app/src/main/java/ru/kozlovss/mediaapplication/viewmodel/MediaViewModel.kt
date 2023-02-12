package ru.kozlovss.mediaapplication.viewmodel

import android.app.Application
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.kozlovss.mediaapplication.dto.Album
import ru.kozlovss.mediaapplication.dto.Track
import ru.kozlovss.mediaapplication.repository.MediaRepository
import ru.kozlovss.mediaapplication.repository.MediaRepositoryImpl

class MediaViewModel(context: Application) : AndroidViewModel(context) {

    private val repository: MediaRepository = MediaRepositoryImpl()
    private val _album = MutableLiveData<Album?>(null)
    val album: LiveData<Album?>
        get() = _album

    val track = MutableLiveData<Track?>(null)

    private var mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    init {
        clearTrack()
        loadAlbum()
    }

    private fun loadAlbum() {
        repository.getAlbumAsync(
            object : MediaRepository.Callback<Album> {
                override fun onSuccess(result: Album) {
                    _album.postValue(result)
                }

                override fun onError(e: Exception) {
                    throw RuntimeException("Альбом не загружен")
                }
            }
        )
    }

    fun setTrackToPlayer () {
        val trackUrl = repository.getTrackUrl(track.value!!.file)
        mediaPlayer.setDataSource(trackUrl)
    }

    private fun clearTrack() {
        track.value = null
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun play(newTrack: Track? = null) {
        newTrack?.let {
            if (track.value != newTrack) {
                track.value = newTrack
            }
        }
        mediaPlayer.start()
    }

    fun isPlaying() = mediaPlayer.isPlaying
}