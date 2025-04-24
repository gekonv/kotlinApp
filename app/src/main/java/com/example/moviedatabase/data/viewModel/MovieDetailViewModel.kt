package com.example.moviedatabase.data.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.moviedatabase.AppContainer
import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.datasource.WatchistDbDatasource
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.local.Watchlist
import com.example.moviedatabase.data.remote.ApiCallResult
import com.example.moviedatabase.data.remote.WebApi
import com.example.moviedatabase.data.repository.MovieRepository
import com.example.moviedatabase.data.repository.WatchlistRepository
import com.example.moviedatabase.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MovieDetailViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val route : Routes.MovieDetail = savedStateHandle.toRoute()
    private val id = route.id

    private val movieRepository = MovieRepository(MovieRemoteDataSource(WebApi.getApiService()))

    private val watchlistRepository = WatchlistRepository(
        watchlistDbDatasource = WatchistDbDatasource(
            watchlistDao = AppContainer.watchlistDatabase.watchlistDao()
        )
    )

    private val _movie = MutableStateFlow(Movie())
    val movie = _movie.asStateFlow()

    init {
        viewModelScope.launch {
            val result = movieRepository.fetchMovieDetail(id)
            if(result is ApiCallResult.Success){
                _movie.value = result.data
            }
        }
    }

    val watchlist: StateFlow<List<Watchlist>> = watchlistRepository.getAllWatchlists.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun addMovieToWatchlists(movie: Movie, watchlistIds: Set<Long>) {
        viewModelScope.launch {
            watchlistIds.forEach { id ->
                watchlistRepository.insertMovie(movie, id)
            }
        }
    }
}