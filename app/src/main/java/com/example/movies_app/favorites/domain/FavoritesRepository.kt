package com.example.movies_app.favorites.domain

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun fetchFavoritesMoviesId(): Flow<Set<String>>
    suspend fun addMovieToFavorites(id: String)
    suspend fun removeMovieFromFavorites(id: String)
}
