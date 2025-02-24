package com.example.movies_app.configuration.remote

import com.example.movies_app.configuration.data.ConfigurationApiModel
import retrofit2.http.GET

interface ConfigurationApiService {
    @GET("configuration")
    suspend fun fetchConfiguration(): ConfigurationApiModel
}
