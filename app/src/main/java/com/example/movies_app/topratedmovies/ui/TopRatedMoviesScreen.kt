package com.example.movies_app.topratedmovies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun TopRatedMoviesScreen(state: TopRatedMoviesState, onItemClick: (String) -> Unit) {
    when (state) {
        is TopRatedMoviesState.Content -> Content(
            state.movies,
            onItemClick
        )

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
        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(items = topMovies,
                key = { it.id }
            ) { movie ->
                MovieItem(movie, onItemClick)
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieUi,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clearAndSetSemantics {
                contentDescription = movie.title
                onClick("show the movie details") {
                    onItemClick(movie.id)
                    true
                }
            }
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = { onItemClick(movie.id) }) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
