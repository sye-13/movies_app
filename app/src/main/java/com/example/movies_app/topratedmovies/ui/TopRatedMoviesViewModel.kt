package com.example.movies_app.topratedmovies.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.topratedmovies.domain.MovieEntity
import com.example.movies_app.topratedmovies.domain.FetchTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Immutable
sealed interface TopRatedMoviesState {
    data object Loading : TopRatedMoviesState
    data class Content(val movies: List<MovieUi>) : TopRatedMoviesState
    data object Error : TopRatedMoviesState
}

data class MovieUi(val id: String, val posterPath: String, val title: String)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(
    private val fetchTopRatedMoviesUseCase: FetchTopRatedMoviesUseCase,
) : ViewModel() {
    private val pageIndex: MutableStateFlow<Int> = MutableStateFlow(1)
    val uiState: StateFlow<TopRatedMoviesState> = pageIndex.flatMapLatest { page ->
        fetchTopRatedMoviesUseCase(page)
            .map<List<MovieEntity>, TopRatedMoviesState> {
                TopRatedMoviesState.Content(it.map { movie ->
                    MovieUi(
                        movie.id,
                        movie.posterPath,
                        movie.title
                    )
                })
            }.catch {
                emit(TopRatedMoviesState.Error)
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TopRatedMoviesState.Loading,
    )
}
