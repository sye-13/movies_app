package com.example.movies_app.movie.data

import com.example.movies_app.movie.domain.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val dataSource: MoviesDataSource
) : MoviesRepository {
    override suspend fun fetchTopRatedMovies(page: Int): TopRatedMoviesApiModel =
        dataSource.fetchTopRatedMovies(page)
}
