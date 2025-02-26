package com.example.movies_app.topratedmovies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage

@Composable
fun TopRatedMoviesDetail(id: String) {
    val viewModel = hiltViewModel<MovieDetailsViewModel, MovieDetailsViewModel.Factory>(
        creationCallback = { factory ->
            factory.create(id = id)
        },
        key = id
    )
    when (val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is MovieDetailsState.Content -> Content(
            movie = uiState.movieUi,
            isAskingFavoriteRemovalConfirmation = uiState.isAskingFavoriteRemovalConfirmation,
            onToggleFavorite = {
                viewModel.onToggleFavorite()
            },
            onDismissFavoriteRemovalConfirmation = {
                viewModel.onDismissFavoriteRemovalConfirmation()
            }
        )

        MovieDetailsState.Error -> Text(
            text = "Error",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )

        MovieDetailsState.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    movie: MovieUi,
    isAskingFavoriteRemovalConfirmation: Boolean,
    onToggleFavorite: () -> Unit,
    onDismissFavoriteRemovalConfirmation: () -> Unit
) {
    if (isAskingFavoriteRemovalConfirmation) {
        FavoritesRemovalConfirmationDialog(
            movie = movie,
            onToggleFavorite = onToggleFavorite,
            onDismissFavoriteRemovalConfirmation = onDismissFavoriteRemovalConfirmation
        )
    }
    TopAppBar(
        title = { Text(text = movie.title) },
        actions = {
            IconButton(
                onClick = { onToggleFavorite() },
            ) {
                Icon(
                    imageVector = if (movie.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (movie.isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = Color.Red
                )
            }
        }
    )
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.originalTitle,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Release: ${movie.releaseDate}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Status: ${movie.status}",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Overview:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoritesRemovalConfirmationDialog(
    movie: MovieUi,
    onToggleFavorite: () -> Unit,
    onDismissFavoriteRemovalConfirmation: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = {
            onDismissFavoriteRemovalConfirmation()
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Are you sure you want to remove ${movie.title} from your favorites?")
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = { onToggleFavorite() }
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}
