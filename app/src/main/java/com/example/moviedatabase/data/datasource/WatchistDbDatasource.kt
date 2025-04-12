package com.example.moviedatabase.data.datasource

import com.example.moviedatabase.data.db.MovieEntity
import com.example.moviedatabase.data.db.WatchlistDao
import com.example.moviedatabase.data.db.WatchlistEntity
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.local.Watchlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WatchistDbDatasource(
    private val watchlistDao: WatchlistDao
) {
    fun getAllWatchlists(): Flow<List<Watchlist>>{
        return watchlistDao.getAllWatchlists().map { watchlistEntities ->
            watchlistEntities.map { e ->
                e.toWatchlist()
            }
        }
    }

    suspend fun updateWatchlist(watchlist: Watchlist) {
        watchlistDao.updateWatchlist(watchlist.toWatchlistEntity())
    }
    suspend fun insertWatchlist(watchlist: Watchlist){
        watchlistDao.insertWatchlist(watchlist.toWatchlistEntity())
    }

    suspend fun deleteWatchlist(watchlist: Watchlist){
        watchlistDao.deleteWatchlist(watchlist.toWatchlistEntity())
    }

    suspend fun insertMovie(movie: Movie){
        watchlistDao.insertMovie(movie.toMovieEntity())
    }

    suspend fun deleteMovie(movie: Movie){
        watchlistDao.deleteMovie(movie.toMovieEntity())
    }

}

fun Movie.toMovieEntity(): MovieEntity{
    return MovieEntity(
        movieId = movieId,
        watchlistId = watchlistId
    )
}

fun MovieEntity.toMovie(): Movie{
    return Movie(
        movieId = movieId,
        watchlistId = watchlistId
    )
}

fun Watchlist.toWatchlistEntity(): WatchlistEntity{
    return WatchlistEntity(
        listId = listId,
        name = name
    )
}

fun WatchlistEntity.toWatchlist(): Watchlist{
    return Watchlist(
        listId = listId,
        name = name
    )
}