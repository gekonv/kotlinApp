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

    suspend fun getWatchlistName(id: Long) = watchlistDbDatasource.getWatchlistName(id)

    fun getAllMovies(watchlistId: Long): Flow<List<Movie>> {
        return watchlistDbDatasource.getAllMovies(watchlistId)
    }

    suspend fun insertMovie(movie: Movie, listId: Long) = watchlistDbDatasource.insertMovie(movie, listId = listId)

    suspend fun deleteMovie(movie: Movie, listId: Long) = watchlistDbDatasource.deleteMovie(movie, listId = listId)
}