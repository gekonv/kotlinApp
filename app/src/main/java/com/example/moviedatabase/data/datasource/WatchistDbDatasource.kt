package com.example.moviedatabase.data.datasource

import com.example.moviedatabase.data.db.GenreConverter
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

    suspend fun insertWatchlist(watchlist: Watchlist){
        watchlistDao.insertWatchlist(watchlist.toWatchlistEntity())
    }

    suspend fun deleteWatchlist(watchlist: Watchlist){
        watchlistDao.deleteWatchlist(watchlist.toWatchlistEntity())
    }

    suspend fun getWatchlistName(id: Long): String{
        return watchlistDao.getName(id)
    }

    fun getAllMovies(id: Long): Flow<List<Movie>>{
        return watchlistDao.getAllMovies(id).map { movieEntities ->
            movieEntities.map { e ->
                e.toMovie()
            }
        }
    }

    suspend fun insertMovie(movie: Movie, listId: Long){
        val count = watchlistDao.movieExists(movie.id, listId)
        if (count == 0) {
            watchlistDao.insertMovie(movie.toMovieEntity(listId))
        }
    }

    suspend fun deleteMovie(movie: Movie, listId: Long){
        watchlistDao.deleteMovie(movieId = movie.id, listId = listId)
    }

}

fun Movie.toMovieEntity(listId: Long): MovieEntity{
    val genresJson = GenreConverter().fromGenreList(this.genres)
    return MovieEntity(
        listId = listId,
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
        genres = genresJson,
        overview = overview
    )
}

fun MovieEntity.toMovie(): Movie{
    val genreList = GenreConverter().toGenreList(this.genres)
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
        genres = genreList,
        overview = overview
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