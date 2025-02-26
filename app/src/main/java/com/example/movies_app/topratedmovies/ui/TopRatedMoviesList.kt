package com.example.movies_app.topratedmovies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopRatedMoviesList(
    topRatedMovies: List<TopRatedMovieUi>,
    onItemClick: (String) -> Unit
) {
    Column {
        TopAppBar(title = { Text(text = "Movies") })
        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(items = topRatedMovies,
                key = { it.id }
            ) { movie ->
                MovieItem(movie, onItemClick)
            }
        }
    }
}

@Composable
private fun MovieItem(
    movie: TopRatedMovieUi,
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
                modifier = Modifier.size(width = 108.dp, height = 160.dp),
                model = movie.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(
                    color = MaterialTheme.colorScheme.surfaceBright
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
