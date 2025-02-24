package com.example.movies_app.topratedmovies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import coil3.compose.AsyncImage

@Composable
fun TopRatedMoviesScreen(state: TopRatedMoviesState, onItemClick: (String) -> Unit) {
    when (state) {
        is TopRatedMoviesState.Content -> Content(state.movies, onItemClick)
        TopRatedMoviesState.Error -> Text(
            text = "Error",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )

        TopRatedMoviesState.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    topMovies: List<MovieUi>,
    onItemClick: (String) -> Unit
) {
    Column {
        TopAppBar(title = { Text(text = "Movies") })
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(
                items = topMovies,
                key = { it.id }
            ) { topMovie ->
                Card(modifier = Modifier.clearAndSetSemantics {
                    contentDescription = topMovie.title
                    onClick("show the movie details") {
                        onItemClick(topMovie.id)
                        true
                    }
                }, onClick = { onItemClick(topMovie.id) }) {
                    AsyncImage(
                        model = topMovie.posterPath,
                        contentDescription = null,
                    )
                    Text(text = topMovie.title)
                }
            }
        }
    }
}
