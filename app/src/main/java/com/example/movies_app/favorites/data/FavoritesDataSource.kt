package com.example.movies_app.favorites.data

import kotlinx.coroutines.flow.Flow

interface FavoritesDataSource {
    fun fetchFavoritesMoviesId(): Flow<Set<String>>
    suspend fun addMovieToFavorites(id: String)
    suspend fun removeMovieFromFavorites(id: String)
}
