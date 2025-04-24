package com.example.moviedatabase.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistDao {
    @Query("SELECT * FROM watchlist ORDER BY listId ASC")
    fun getAllWatchlists(): Flow<List<WatchlistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(watchlistEntity: WatchlistEntity): Long

    @Delete
    suspend fun deleteWatchlist(watchlist: WatchlistEntity)

    @Query("SELECT name FROM watchlist WHERE listId = :id")
    suspend fun getName(id: Long): String

    @Query("SELECT * FROM movies WHERE listId = :id")
    fun getAllMovies(id: Long): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE id = :movieId AND listId = :listId")
    suspend fun deleteMovie(movieId: Int, listId: Long)

    @Query("SELECT COUNT(*) FROM movies WHERE id = :movieId AND listId = :listId")
    suspend fun movieExists(movieId: Int, listId: Long): Int
}