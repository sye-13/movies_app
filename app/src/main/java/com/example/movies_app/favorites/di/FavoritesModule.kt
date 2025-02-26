package com.example.movies_app.favorites.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.movies_app.favorites.data.FavoritesDataSource
import com.example.movies_app.favorites.data.FavoritesRepositoryImpl
import com.example.movies_app.favorites.data.LocalFavoritesDataSource
import com.example.movies_app.favorites.domain.AddMovieToFavoritesUseCase
import com.example.movies_app.favorites.domain.FavoritesRepository
import com.example.movies_app.favorites.domain.FetchFavoritesMoviesIdUseCase
import com.example.movies_app.favorites.domain.RemoveMovieFromFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesModule {
    private const val DATA_STORE_NAME = "FAVORITE_MOVIES"

    @Singleton
    @FavoritesPreferencesDataStore
    @Provides
    fun provideFavoritesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(DATA_STORE_NAME)
        }
    }

    @Singleton
    @Provides
    fun provideFavoritesDataSource(
        @FavoritesPreferencesDataStore dataStore: DataStore<Preferences>
    ): FavoritesDataSource {
        return LocalFavoritesDataSource(dataStore)
    }

    @Singleton
    @Provides
    fun provideFavoritesRepository(favoritesDataSource: FavoritesDataSource): FavoritesRepository {
        return FavoritesRepositoryImpl(favoritesDataSource)
    }

    @Provides
    fun provideFetchFavoritesMoviesIdUseCase(
        favoritesRepository: FavoritesRepository
    ): FetchFavoritesMoviesIdUseCase {
        return FetchFavoritesMoviesIdUseCase(favoritesRepository)
    }

    @Provides
    fun provideAddMovieToFavoritesUseCase(
        favoritesRepository: FavoritesRepository
    ): AddMovieToFavoritesUseCase {
        return AddMovieToFavoritesUseCase(favoritesRepository)
    }

    @Provides
    fun provideRemoveMovieFromFavoritesUseCase(
        favoritesRepository: FavoritesRepository
    ): RemoveMovieFromFavoritesUseCase {
        return RemoveMovieFromFavoritesUseCase(favoritesRepository)
    }
}
