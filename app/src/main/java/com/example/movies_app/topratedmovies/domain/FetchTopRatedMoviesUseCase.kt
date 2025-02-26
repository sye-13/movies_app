package com.example.movies_app.topratedmovies.domain

import com.example.movies_app.configuration.domain.ConfigurationRepository
import com.example.movies_app.movie.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val configurationRepository: ConfigurationRepository,
) {
    operator fun invoke(page: Int, width: String = "w185"): Flow<List<TopRatedMovieEntity>> =
        combine(
            flow { emit(moviesRepository.fetchTopRatedMovies(page)) },
            configurationRepository.fetchConfiguration(),
        ) { movies, configuration ->
            val baseUrl = "${configuration.images.baseUrl}$width"
            movies.results.map {
                TopRatedMovieEntity(
                    it.id,
                    "$baseUrl${it.posterPath}",
                    it.title,
                )
            }
        }
}
