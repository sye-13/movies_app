package com.example.movies_app.configuration.remote

import com.example.movies_app.configuration.data.ConfigurationApiModel
import com.example.movies_app.configuration.data.ConfigurationDataSource
import javax.inject.Inject

class RemoteConfigurationDataSource @Inject constructor(
    private val apiService: ConfigurationApiService
) : ConfigurationDataSource {
    override suspend fun fetchConfiguration(): ConfigurationApiModel =
        apiService.fetchConfiguration()
}
