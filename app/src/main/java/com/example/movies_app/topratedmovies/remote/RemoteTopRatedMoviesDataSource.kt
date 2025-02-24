package com.example.movies_app.topratedmovies.remote

import com.example.movies_app.topratedmovies.data.TopRatedMoviesApiModel
import com.example.movies_app.topratedmovies.data.TopRatedMoviesDataSource
import javax.inject.Inject

class RemoteTopRatedMoviesDataSource @Inject constructor(
    private val apiService: TopRatedMoviesApiService
) : TopRatedMoviesDataSource {
    override suspend fun fetchTopRatedMovies(page: Int): TopRatedMoviesApiModel = apiService.fetchTopRatedMovies(page)
}
