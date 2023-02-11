package ru.kozlovss.mediaapplication.repository

import com.google.gson.Gson
import okhttp3.*
import ru.kozlovss.mediaapplication.dto.Album
import java.io.IOException
import java.util.concurrent.TimeUnit

class MediaRepositoryImpl : MediaRepository {

    private val gson = Gson()
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    override suspend fun getTrack() {
        TODO("Not yet implemented")
    }

    override fun getAlbumAsync(callback: MediaRepository.Callback<Album>) {
        val request: Request = Request.Builder()
            .url(ALBUM_URL)
            .build()

        okHttpClient.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    callback.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string() ?: throw RuntimeException("body is null")
                    try {
                        callback.onSuccess(gson.fromJson(body, Album::class.java))
                    } catch (e: Exception) {
                        throw RuntimeException("Альбом не загружен")
                    }
                }
            })
    }

    companion object {
        private const val ALBUM_URL =
            "https://github.com/netology-code/andad-homeworks/raw/master/09_multimedia/data/album.json"
        private const val TRACK_URL =
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/"

        fun getTrackUrl(trackUrl: String) = "${TRACK_URL}$trackUrl"
    }
}