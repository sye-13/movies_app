package com.example.movies_app.topratedmovies.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object TopRatedMovies

fun NavGraphBuilder.topRatedMoviesScreen() {
    composable<TopRatedMovies> {
        TopRatedMoviesRoute()
    }
}

@Composable
fun TopRatedMoviesRoute() {
    val viewModel = hiltViewModel<TopRatedMoviesViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    TopRatedMoviesScreen(uiState = uiState)
}
