package com.example.movies_app.topratedmovies.ui

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize

@Composable
fun TopRatedMoviesScreen(uiState: TopRatedMoviesState) {
    when (uiState) {
        is TopRatedMoviesState.Content -> Content(
            topRatedMovies = uiState.movies,
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

@Parcelize
class SelectedMovieId(val id: String) : Parcelable

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun Content(
    topRatedMovies: List<TopRatedMovieUi>
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<SelectedMovieId>()
    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                TopRatedMoviesList(
                    topRatedMovies = topRatedMovies,
                    onItemClick = { id ->
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, SelectedMovieId(id))
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                navigator.currentDestination?.content?.id?.let {
                    TopRatedMoviesDetail(
                        id = it
                    )
                }
            }
        }
    )
}
