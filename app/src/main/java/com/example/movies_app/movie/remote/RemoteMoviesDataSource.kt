package com.example.movies_app.movie.remote

import com.example.movies_app.movie.data.TopRatedMoviesApiModel
import com.example.movies_app.movie.data.MoviesDataSource
import javax.inject.Inject

class RemoteMoviesDataSource @Inject constructor(
    private val apiService: MovieApiService
) : MoviesDataSource {
    override suspend fun fetchTopRatedMovies(page: Int): TopRatedMoviesApiModel = apiService.fetchTopRatedMovies(page)
}
