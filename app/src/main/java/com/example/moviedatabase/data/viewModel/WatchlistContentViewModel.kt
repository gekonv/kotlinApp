package com.example.moviedatabase.data.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.moviedatabase.AppContainer
import com.example.moviedatabase.data.datasource.WatchistDbDatasource
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.local.Watchlist
import com.example.moviedatabase.data.remote.ApiCallResult
import com.example.moviedatabase.data.repository.WatchlistRepository
import com.example.moviedatabase.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WatchlistContentViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val route : Routes.WatchlistContent = savedStateHandle.toRoute()
    private val id = route.watchlistId

    private val _watchlistName = MutableStateFlow("")
    val watchlistName = _watchlistName.asStateFlow()

    private val watchlistContentRepository = WatchlistRepository(
        watchlistDbDatasource = WatchistDbDatasource(
            watchlistDao = AppContainer.watchlistDatabase.watchlistDao()
        )
    )

    init {
        viewModelScope.launch {
            val result = watchlistContentRepository.getWatchlistName(id)
            _watchlistName.value = result

        }
    }

    val movies: StateFlow<List<Movie>> = watchlistContentRepository.getAllMovies(id).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun deleteMovie(movie: Movie){
        viewModelScope.launch {
            watchlistContentRepository.deleteMovie(movie, id)
        }
    }
}