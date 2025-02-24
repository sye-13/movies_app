package com.example.movies_app.configuration.domain

import com.example.movies_app.configuration.data.ConfigurationApiModel
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    fun fetchConfiguration(): Flow<ConfigurationApiModel>
}
