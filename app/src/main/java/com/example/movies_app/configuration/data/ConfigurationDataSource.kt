package com.example.movies_app.configuration.data

interface ConfigurationDataSource {
    suspend fun fetchConfiguration(): ConfigurationApiModel
}
