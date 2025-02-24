package com.example.movies_app.topratedmovies.di

import com.example.movies_app.configuration.domain.ConfigurationRepository
import com.example.movies_app.topratedmovies.remote.TopRatedMoviesApiService
import com.example.movies_app.topratedmovies.data.TopRatedMoviesDataSource
import com.example.movies_app.topratedmovies.data.TopRatedRatedMoviesRepositoryImpl
import com.example.movies_app.topratedmovies.domain.FetchTopRatedMoviesUseCase
import com.example.movies_app.topratedmovies.domain.TopRatedMoviesRepository
import com.example.movies_app.topratedmovies.remote.RemoteTopRatedMoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TopRatedMoviesModule {

    @Singleton
    @Provides
    fun provideTopRatedMoviesApiService(retrofit: Retrofit): TopRatedMoviesApiService {
        return retrofit.create(TopRatedMoviesApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideTopRatedMoviesDataSource(topRatedMoviesApiService: TopRatedMoviesApiService): TopRatedMoviesDataSource {
        return RemoteTopRatedMoviesDataSource(topRatedMoviesApiService)
    }

    @Singleton
    @Provides
    fun provideTopRatedMoviesRepository(topRatedMoviesDataSource: TopRatedMoviesDataSource): TopRatedMoviesRepository {
        return TopRatedRatedMoviesRepositoryImpl(topRatedMoviesDataSource)
    }

    @Provides
    fun provideFetchTopRatedMoviesUseCase(
        topRatedMoviesRepository: TopRatedMoviesRepository,
        configurationRepository: ConfigurationRepository
    ): FetchTopRatedMoviesUseCase {
        return FetchTopRatedMoviesUseCase(topRatedMoviesRepository, configurationRepository)
    }
}
