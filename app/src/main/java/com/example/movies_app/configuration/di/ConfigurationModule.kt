package com.example.movies_app.configuration.di

import com.example.movies_app.configuration.data.ConfigurationDataSource
import com.example.movies_app.configuration.data.ConfigurationRepositoryImpl
import com.example.movies_app.configuration.domain.ConfigurationRepository
import com.example.movies_app.configuration.remote.ConfigurationApiService
import com.example.movies_app.configuration.remote.RemoteConfigurationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigurationModule {
    @Singleton
    @Provides
    fun provideConfigurationApiService(retrofit: Retrofit): ConfigurationApiService {
        return retrofit.create(ConfigurationApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideConfigurationDataSource(
        configurationApiService: ConfigurationApiService
    ): ConfigurationDataSource {
        return RemoteConfigurationDataSource(configurationApiService)
    }

    @Singleton
    @Provides
    fun provideConfigurationRepository(
        configurationDataSource: ConfigurationDataSource
    ): ConfigurationRepository {
        return ConfigurationRepositoryImpl(configurationDataSource)
    }
}
