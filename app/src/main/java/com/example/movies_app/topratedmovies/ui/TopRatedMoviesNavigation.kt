package com.example.movies_app.topratedmovies.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object TopRatedMovies

fun NavGraphBuilder.topRatedMoviesScreen(onItemClick: (String) -> Unit) {
    composable<TopRatedMovies> {
        TopRatedMoviesRoute(onItemClick = onItemClick)
    }
}

@Composable
fun TopRatedMoviesRoute(onItemClick: (String) -> Unit) {
    val viewModel = hiltViewModel<TopRatedMoviesViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    TopRatedMoviesScreen(
        state = state,
        onItemClick = onItemClick
    )
}
