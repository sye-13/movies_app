package com.example.movies_app.favorites.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.movies_app.favorites.di.FavoritesPreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalFavoritesDataSource @Inject constructor(
    @FavoritesPreferencesDataStore
    private val dataStore: DataStore<Preferences>
) : FavoritesDataSource {
    private val favoritesKey = stringSetPreferencesKey("FAVORITES")

    private suspend fun saveFavorites(favoriteIds: Set<String>) {
        dataStore.edit { preferences ->
            preferences[favoritesKey] = favoriteIds
        }
    }

    override fun fetchFavoritesMoviesId(): Flow<Set<String>> {
        return dataStore.data.map { preferences ->
            preferences[favoritesKey] ?: emptySet()
        }
    }

    override suspend fun addMovieToFavorites(id: String) {
        val currentFavoriteIds = fetchFavoritesMoviesId().first()
        if (id in currentFavoriteIds) {
            return
        }
        val updatedFavoriteIds = currentFavoriteIds.toMutableSet().apply {
            add(id)
        }
        saveFavorites(updatedFavoriteIds)
    }

    override suspend fun removeMovieFromFavorites(id: String) {
        val currentFavoriteIds = fetchFavoritesMoviesId().first()
        if (id !in currentFavoriteIds) {
            return
        }
        val updatedFavoriteIds = currentFavoriteIds.toMutableSet().apply {
            remove(id)
        }
        saveFavorites(updatedFavoriteIds)
    }
}
