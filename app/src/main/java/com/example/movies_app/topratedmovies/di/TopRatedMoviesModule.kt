package com.example.movies_app.topratedmovies.di

import com.example.movies_app.configuration.domain.ConfigurationRepository
import com.example.movies_app.favorites.domain.FetchFavoritesMoviesIdUseCase
import com.example.movies_app.movie.domain.MoviesRepository
import com.example.movies_app.topratedmovies.domain.FetchMovieDetailsUseCase
import com.example.movies_app.topratedmovies.domain.FetchTopRatedMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TopRatedMoviesModule {

    @Provides
    fun provideFetchMoviesUseCase(
        moviesRepository: MoviesRepository,
        configurationRepository: ConfigurationRepository
    ): FetchTopRatedMoviesUseCase {
        return FetchTopRatedMoviesUseCase(moviesRepository, configurationRepository)
    }

    @Provides
    fun provideFetchMovieDetailsUseCase(
        moviesRepository: MoviesRepository,
        configurationRepository: ConfigurationRepository,
        fetchFavoritesMoviesIdUseCase: FetchFavoritesMoviesIdUseCase
    ): FetchMovieDetailsUseCase {
        return FetchMovieDetailsUseCase(
            moviesRepository,
            configurationRepository,
            fetchFavoritesMoviesIdUseCase
        )
    }
}