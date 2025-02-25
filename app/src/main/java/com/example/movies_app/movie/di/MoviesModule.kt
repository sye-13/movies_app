package com.example.movies_app.movie.di

import com.example.movies_app.movie.data.MoviesDataSource
import com.example.movies_app.movie.data.MoviesRepositoryImpl
import com.example.movies_app.movie.domain.MoviesRepository
import com.example.movies_app.movie.remote.MovieApiService
import com.example.movies_app.movie.remote.RemoteMoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Singleton
    @Provides
    fun provideMoviesApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoviesDataSource(movieApiService: MovieApiService): MoviesDataSource {
        return RemoteMoviesDataSource(movieApiService)
    }

    @Singleton
    @Provides
    fun provideMoviesRepository(moviesDataSource: MoviesDataSource): MoviesRepository {
        return MoviesRepositoryImpl(moviesDataSource)
    }
}
