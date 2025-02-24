package com.example.movies_app.topratedmovies.domain

import com.example.movies_app.configuration.domain.ConfigurationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchTopRatedMoviesUseCase @Inject constructor(
    private val topRatedMoviesRepository: TopRatedMoviesRepository,
    private val configurationRepository: ConfigurationRepository,
) {
    operator fun invoke(page: Int, width: String = "w185"): Flow<List<MovieEntity>> = combine(
        flow { emit(topRatedMoviesRepository.fetchTopRatedMovies(page)) },
        configurationRepository.fetchConfiguration(),
    ) { movies, configuration ->
        val baseUrl = "${configuration.images.baseUrl}$width"
        movies.results.map {
            MovieEntity(
                it.id,
                "$baseUrl${it.posterPath}",
                it.title,
            )
        }
    }
}
