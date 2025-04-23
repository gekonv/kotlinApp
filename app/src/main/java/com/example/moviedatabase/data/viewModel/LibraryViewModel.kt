package com.example.moviedatabase.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.AppContainer
import com.example.moviedatabase.data.datasource.WatchistDbDatasource
import com.example.moviedatabase.data.local.Watchlist
import com.example.moviedatabase.data.repository.WatchlistRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LibraryViewModel() : ViewModel(){
    private val repository = WatchlistRepository(
        watchlistDbDatasource = WatchistDbDatasource(
            watchlistDao = AppContainer.watchlistDatabase.watchlistDao()
        )
    )

    val watchlist: StateFlow<List<Watchlist>> = repository.getAllWatchlists.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun deleteWatchlist(watchlist: Watchlist){
        viewModelScope.launch {
            repository.deleteWatchlist(watchlist)
        }
    }

    fun createWatchlist(name: String){
        viewModelScope.launch {
            repository.insertWatchlist(name)
        }
    }
}