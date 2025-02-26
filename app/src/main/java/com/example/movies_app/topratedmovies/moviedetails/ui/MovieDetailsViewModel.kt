package com.example.movies_app.topratedmovies.moviedetails.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.movies_app.favorites.domain.AddMovieToFavoritesUseCase
import com.example.movies_app.favorites.domain.RemoveMovieFromFavoritesUseCase
import com.example.movies_app.topratedmovies.moviedetails.domain.FetchMovieDetailsUseCase
import com.example.movies_app.topratedmovies.moviedetails.domain.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface MovieDetailsState {
    data object Loading : MovieDetailsState
    data class Content(
        val movieUi: MovieUi,
        val isAskingFavoriteRemovalConfirmation: Boolean
    ) : MovieDetailsState

    data object Error : MovieDetailsState
}

data class MovieUi(
    val id: String,
    val posterPath: String,
    val title: String,
    val originalTitle: String,
    val releaseDate: String,
    val status: String,
    val homepage: String,
    val overview: String,
    val isFavorite: Boolean
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase
) : ViewModel() {
    private val movieDetails = savedStateHandle.toRoute<MovieDetails>()

    private val askFavoriteRemovalConfirmation: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    val uiState: StateFlow<MovieDetailsState> = combine(
        flow { emit(movieDetails.id) },
        askFavoriteRemovalConfirmation
    ) { id, askConfirmation ->
        Pair(id, askConfirmation)
    }
        .flatMapLatest { value ->
            fetchMovieDetailsUseCase(value.first).map<MovieEntity, MovieDetailsState> { entity ->
                MovieDetailsState.Content(
                    movieUi = MovieUi(
                        id = entity.id,
                        posterPath = entity.posterPath,
                        title = entity.title,
                        originalTitle = entity.originalTitle,
                        releaseDate = entity.releaseDate,
                        status = entity.status,
                        homepage = entity.homepage,
                        overview = entity.overview,
                        isFavorite = entity.isFavorite
                    ),
                    isAskingFavoriteRemovalConfirmation = value.second
                )
            }.catch {
                emit(MovieDetailsState.Error)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailsState.Loading,
        )

    fun onToggleFavorite() {
        val state = uiState.value
        if (state !is MovieDetailsState.Content) {
            return
        }
        val movie = state.movieUi
        if (movie.isFavorite && !state.isAskingFavoriteRemovalConfirmation) {
            askFavoriteRemovalConfirmation.value = true
            return
        }
        viewModelScope.launch {
            if (movie.isFavorite && state.isAskingFavoriteRemovalConfirmation) {
                removeMovieFromFavoritesUseCase(movie.id)
                askFavoriteRemovalConfirmation.value = false
            } else {
                addMovieToFavoritesUseCase(movie.id)
            }
        }
    }

    fun onDismissFavoriteRemovalConfirmation() {
        val state = uiState.value
        if (state !is MovieDetailsState.Content) {
            return
        }
        if (state.isAskingFavoriteRemovalConfirmation) {
            askFavoriteRemovalConfirmation.value = false
        }
    }
}
