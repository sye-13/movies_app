package com.example.movies_app.configuration.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationApiModel(
    val images: ConfigurationImage
)

@Serializable
data class ConfigurationImage(
    @SerialName("base_url")
    val baseUrl: String
)
