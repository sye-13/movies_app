package com.example.movies_app.topratedmovies.data

import com.example.movies_app.topratedmovies.domain.TopRatedMoviesRepository
import javax.inject.Inject

class TopRatedRatedMoviesRepositoryImpl @Inject constructor(
    private val dataSource: TopRatedMoviesDataSource
) : TopRatedMoviesRepository {
    override suspend fun fetchTopRatedMovies(page: Int): TopRatedMoviesApiModel =
        dataSource.fetchTopRatedMovies(page)
}
