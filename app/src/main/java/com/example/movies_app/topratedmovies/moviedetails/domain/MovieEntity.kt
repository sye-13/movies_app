package com.example.movies_app.topratedmovies.moviedetails.domain

data class MovieEntity(
    val id: String,
    val posterPath: String,
    val title: String,
    val originalTitle: String,
    val releaseDate: String,
    val status: String,
    val homepage: String,
    val overview: String
)
