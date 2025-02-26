package com.example.movies_app.favorites.domain

import javax.inject.Inject

class RemoveMovieFromFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(id: String) = favoritesRepository.removeMovieFromFavorites(id)
}
