package com.example.movies_app.favorites.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFavoritesMoviesIdUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<Set<String>> = favoritesRepository.fetchFavoritesMoviesId()
}
