package com.example.movies_app.favorites.data

import com.example.movies_app.favorites.domain.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dataSource: FavoritesDataSource
) : FavoritesRepository {
    override fun fetchFavoritesMoviesId(): Flow<Set<String>> = dataSource.fetchFavoritesMoviesId()

    override suspend fun addMovieToFavorites(id: String) = dataSource.addMovieToFavorites(id)

    override suspend fun removeMovieFromFavorites(id: String) =
        dataSource.removeMovieFromFavorites(id)
}
