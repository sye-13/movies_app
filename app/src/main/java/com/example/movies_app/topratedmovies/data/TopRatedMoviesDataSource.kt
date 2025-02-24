package com.example.movies_app.topratedmovies.data

interface TopRatedMoviesDataSource {
    suspend fun fetchTopRatedMovies(page: Int): TopRatedMoviesApiModel
}
