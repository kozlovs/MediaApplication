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

    fun switch(newTrack: Track? = null) {
        album.value?.let {
            if (mediaPlayer == null) initializePlayer()
            if (newTrack == null) {
                if (mediaPlayer?.isPlaying == true) {
                    pause()
                } else {
                    play()
                }
            } else {
                if (!isTrackSet(newTrack)) {
                    play(newTrack)
                } else {
                    if (mediaPlayer?.isPlaying == true) {
                        pause()
                    } else {
                        play()
                    }
                }
            }
        }
    }

    private fun pause() {
        mediaPlayer?.pause()
        changeState()
    }

    private fun play(newTrack: Track? = null) {
        if (newTrack == null && track.value == null) {
            album.value?.tracks?.get(0)?.let { setTrackToPlayer(it) }
        }
        if (newTrack != null && !isTrackSet(newTrack)) {
            playerStop()
            initializePlayer()
            setTrackToPlayer(newTrack)
        }
        mediaPlayer?.start()
        changeState()
    }

    private fun changeState() {
        _isPlaying.value = mediaPlayer?.isPlaying
        val newTracks = _album.value?.tracks!!.toMutableList()
        album.value?.tracks?.let { list ->
            list.forEachIndexed { index, t ->
                if (t == track.value && mediaPlayer?.isPlaying == true) {
                    newTracks[index] = t.copy(isPlaying = true)
                } else {
                    newTracks[index] = t.copy(isPlaying = false)
                }
            }
        }
        _album.value = album.value?.copy(tracks = newTracks)
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

    private fun clearTrack() {
        track.value = null
    }

    private fun isTrackSet(checkedTrack: Track) = track.value == checkedTrack

    private fun playerStop() {
        mediaPlayer?.release()
        mediaPlayer = null
        clearTrack()
    }
}