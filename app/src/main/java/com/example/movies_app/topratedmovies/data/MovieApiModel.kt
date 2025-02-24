package com.example.movies_app.topratedmovies.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiModel(
    val id: String,
    @SerialName("poster_path")
    val posterPath: String,
    val title: String
)
