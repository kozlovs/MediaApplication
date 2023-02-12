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

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean>
        get() = _isPlaying

    val track = MutableLiveData<Track?>(null)

    private var mediaPlayer: MediaPlayer? = null

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

    private fun clearTrack() {
        track.value = null
    }

    fun pause() {
        _isPlaying.value = false
    }

    fun play(newTrack: Track? = null) {
        if (mediaPlayer == null) {
            initializePlayer()
        } else {
            newTrack?.let {
                if (!isTrackSet(it)) {
                    playerStop()
                    initializePlayer()
                }
            }
        }
        newTrack?.let {
            if (!isTrackSet(it)) {
                setTrackToPlayer(newTrack)
            }
        }
        _isPlaying.value = true
    }

    private fun initializePlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setOnCompletionListener {
                playerStop()
            }
        }
    }

    private fun setTrackToPlayer(newTrack: Track) {
        track.value = newTrack
        val trackUrl = repository.getTrackUrl(track.value!!.file)
        mediaPlayer?.setDataSource(trackUrl)
        mediaPlayer?.prepare()
    }

    private fun playerStop() {
        mediaPlayer?.release()
        mediaPlayer = null
        clearTrack()
    }

    fun playerPlay() {
        mediaPlayer?.start()
    }

    fun playerPause() {
        mediaPlayer?.pause()
    }

    fun isTrackSet(checkedTrack: Track) = track.value == checkedTrack
}