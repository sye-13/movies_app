package com.example.movies_app.movie.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopMovieApiModel(
    val id: String,
    @SerialName("poster_path")
    val posterPath: String,
    val title: String
)
