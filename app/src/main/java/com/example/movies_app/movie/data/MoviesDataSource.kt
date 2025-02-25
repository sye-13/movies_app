package com.example.movies_app.movie.data

interface MoviesDataSource {
    suspend fun fetchTopRatedMovies(page: Int): TopRatedMoviesApiModel
}
