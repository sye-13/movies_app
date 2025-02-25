package com.example.movies_app.topratedmovies.moviedetails.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.movies_app.topratedmovies.moviedetails.domain.FetchMovieDetailsUseCase
import com.example.movies_app.topratedmovies.moviedetails.domain.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Immutable
sealed interface MovieDetailsState {
    data object Loading : MovieDetailsState
    data class Content(val movieUi: MovieUi) : MovieDetailsState
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
    val overview: String
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
) : ViewModel() {
    private val movieDetails = savedStateHandle.toRoute<MovieDetails>()

    val uiState: StateFlow<MovieDetailsState> = flow { emit(movieDetails.id) }
        .flatMapLatest { id ->
            fetchMovieDetailsUseCase(id).map<MovieEntity, MovieDetailsState> { entity ->
                MovieDetailsState.Content(
                    MovieUi(
                        id = entity.id,
                        posterPath = entity.posterPath,
                        title = entity.title,
                        originalTitle = entity.originalTitle,
                        releaseDate = entity.releaseDate,
                        status = entity.status,
                        homepage = entity.homepage,
                        overview = entity.overview
                    )
                )
            }.catch {
                emit(MovieDetailsState.Error)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailsState.Loading,
        )
}
