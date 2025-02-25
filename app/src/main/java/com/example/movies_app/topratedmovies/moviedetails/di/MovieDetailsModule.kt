package com.example.movies_app.topratedmovies.moviedetails.di

import com.example.movies_app.configuration.domain.ConfigurationRepository
import com.example.movies_app.movie.domain.MoviesRepository
import com.example.movies_app.topratedmovies.moviedetails.domain.FetchMovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    fun provideFetchMovieDetailsUseCase(
        moviesRepository: MoviesRepository,
        configurationRepository: ConfigurationRepository
    ): FetchMovieDetailsUseCase {
        return FetchMovieDetailsUseCase(moviesRepository, configurationRepository)
    }
}
