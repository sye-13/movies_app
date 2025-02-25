package com.example.movies_app

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.movies_app.topratedmovies.moviedetails.ui.MovieDetails
import com.example.movies_app.topratedmovies.moviedetails.ui.movieDetailsScreen
import com.example.movies_app.topratedmovies.ui.TopRatedMovies
import com.example.movies_app.topratedmovies.ui.topRatedMoviesScreen

@Composable
fun App() {
    Surface(
        modifier = Modifier.safeDrawingPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()
        NavHost(navController, startDestination = TopRatedMovies) {
            topRatedMoviesScreen(
                onItemClick = { id ->
                    navController.navigate(MovieDetails(id))
                }
            )
            movieDetailsScreen()
        }
    }
}
