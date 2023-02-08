package ru.kozlovss.mediaapplication.api

import retrofit2.Response
import retrofit2.http.GET
import ru.kozlovss.mediaapplication.dto.Album

interface ApiService {

    @GET
    suspend fun getAlbum(): Response<Album>
}