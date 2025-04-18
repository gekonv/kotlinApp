package com.example.moviedatabase.data.repository

import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.remote.Movie

private const val API_KEY = "4e89cba531c83875ef428d3d234aec8f"

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {
    suspend fun fetchPopularMovies(): List<Movie>{
        return movieRemoteDataSource.fetchPopularMovies(API_KEY)
    }

    suspend fun fetchNowPlayingMovies(): List<Movie>{
        return movieRemoteDataSource.fetchNowPlayingMovies(API_KEY)
    }

    suspend fun fetchUpcoming(): List<Movie>{
        return movieRemoteDataSource.fetchUpcoming(API_KEY)
    }
}