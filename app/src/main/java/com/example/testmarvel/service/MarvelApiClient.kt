package com.example.testmarvel.service

import com.example.testmarvel.BuildConfig
import com.example.testmarvel.model.Comic
import com.example.testmarvel.utils.MarvelAPIUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MarvelApiClient {
    private const val BASE_URL = BuildConfig.MARVEL_URL

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(MarvelApiService::class.java)

    suspend fun getComics(): List<Comic>? {
        val timestamp = System.currentTimeMillis().toString()
        val publicKey = BuildConfig.PUBLIC_KEY
        val privateKey = BuildConfig.PRIVATE_KEY
        val hash: String = MarvelAPIUtils.generateHash(timestamp, publicKey, privateKey).toString()
        val response = apiService.getComics(publicKey,timestamp,hash)

        return if (response.isSuccessful) {
            response.body()?.data?.results
        } else {
            null
        }
    }
}