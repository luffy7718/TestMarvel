package com.example.testmarvel.service

import com.example.testmarvel.model.ComicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("comics")
    suspend fun getComics(@Query("apikey") apiKey: String,@Query("ts") timestamp: String,@Query("hash") hash: String): Response<ComicResponse>
}