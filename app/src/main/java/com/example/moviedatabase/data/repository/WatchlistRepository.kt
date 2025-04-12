package com.example.moviedatabase.data.repository

import com.example.moviedatabase.data.datasource.WatchistDbDatasource
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.local.Watchlist
import kotlinx.coroutines.flow.Flow

class WatchlistRepository(private val watchlistDbDatasource: WatchistDbDatasource){
    val getAllWatchlists: Flow<List<Watchlist>> = watchlistDbDatasource.getAllWatchlists()

    suspend fun insertWatchlist(name: String) = watchlistDbDatasource.insertWatchlist(Watchlist(name = name))

    suspend fun renameWatchlist(watchlist: Watchlist) = watchlistDbDatasource.updateWatchlist(watchlist)

    suspend fun deleteWatchlist(watchlist: Watchlist) = watchlistDbDatasource.deleteWatchlist(watchlist)

    suspend fun addMovie(watchlistId: Long) = watchlistDbDatasource.insertMovie(Movie(watchlistId = watchlistId))

    suspend fun deleteMovie(movie: Movie) = watchlistDbDatasource.deleteMovie(movie)
}