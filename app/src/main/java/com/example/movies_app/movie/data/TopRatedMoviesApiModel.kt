package com.example.movies_app.movie.data

import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMoviesApiModel(val results: List<TopMovieApiModel>)
