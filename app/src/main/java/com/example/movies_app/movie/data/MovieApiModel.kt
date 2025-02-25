package com.example.movies_app.movie.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiModel(
    val id: String,
    @SerialName("poster_path")
    val posterPath: String,
    val title: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("release_date")
    val releaseDate: String,
    val status: String,
    val homepage: String = "",
    val overview: String,
)
