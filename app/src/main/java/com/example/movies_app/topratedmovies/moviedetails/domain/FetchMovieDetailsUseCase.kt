package com.example.movies_app.topratedmovies.moviedetails.domain

import com.example.movies_app.configuration.domain.ConfigurationRepository
import com.example.movies_app.favorites.domain.FetchFavoritesMoviesIdUseCase
import com.example.movies_app.movie.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val configurationRepository: ConfigurationRepository,
    private val fetchFavoritesMoviesIdUseCase: FetchFavoritesMoviesIdUseCase
) {
    operator fun invoke(id: String, width: String = "w185"): Flow<MovieEntity> = combine(
        flow { emit(moviesRepository.fetchMovie(id)) },
        configurationRepository.fetchConfiguration(),
        fetchFavoritesMoviesIdUseCase()
    ) { movie, configuration, favorites ->
        val baseUrl = "${configuration.images.baseUrl}$width"
        MovieEntity(
            id = movie.id,
            posterPath = "$baseUrl${movie.posterPath}",
            title = movie.title,
            originalTitle = movie.originalTitle,
            releaseDate = movie.releaseDate,
            status = movie.status,
            homepage = movie.homepage,
            overview = movie.overview,
            isFavorite = movie.id in favorites
        )
    }
}