package com.example.movies_app.topratedmovies.moviedetails.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(val id: String)

fun NavGraphBuilder.movieDetailsScreen() {
    composable<MovieDetails> {
        MovieDetailsRoute()
    }
}

@Composable
fun MovieDetailsRoute() {
    val viewModel = hiltViewModel<MovieDetailsViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    MovieDetailsScreen(
        state = state,
        onToggleFavorite = {
            viewModel.onToggleFavorite()
        },
        onDismissFavoriteRemovalConfirmation = {
            viewModel.onDismissFavoriteRemovalConfirmation()
        }
    )
}
