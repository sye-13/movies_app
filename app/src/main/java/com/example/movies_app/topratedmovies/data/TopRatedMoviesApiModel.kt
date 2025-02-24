package com.example.movies_app.topratedmovies.data

import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMoviesApiModel(val results: List<MovieApiModel>)
