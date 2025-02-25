package com.example.movies_app.movie.domain

import com.example.movies_app.movie.data.TopRatedMoviesApiModel

interface MoviesRepository {
    suspend fun fetchTopRatedMovies(
        page: Int
    ): TopRatedMoviesApiModel
}
