package com.example.movies_app.topratedmovies.domain

import com.example.movies_app.topratedmovies.data.TopRatedMoviesApiModel

interface TopRatedMoviesRepository {
    suspend fun fetchTopRatedMovies(
        page: Int
    ): TopRatedMoviesApiModel
}
