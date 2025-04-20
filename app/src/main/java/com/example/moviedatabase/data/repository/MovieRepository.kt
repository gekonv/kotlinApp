package com.example.moviedatabase.data.repository

import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.remote.ApiCallResult
import com.example.moviedatabase.data.local.Movie

private const val API_KEY = "4e89cba531c83875ef428d3d234aec8f"

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {
    suspend fun fetchPopularMovies(): ApiCallResult<List<Movie>>{
        return movieRemoteDataSource.fetchPopularMovies(API_KEY)
    }

    suspend fun fetchNowPlayingMovies(): ApiCallResult<List<Movie>>{
        return movieRemoteDataSource.fetchNowPlayingMovies(API_KEY)
    }

    suspend fun fetchUpcoming(): ApiCallResult<List<Movie>>{
        return movieRemoteDataSource.fetchUpcoming(API_KEY)
    }

    suspend fun fetchMovieDetail(id: Int): ApiCallResult<Movie>{
        return movieRemoteDataSource.fetchMovieDetail(id, API_KEY)
    }

    suspend fun fetchMovieSearch(query: String): ApiCallResult<List<Movie>>{
        return movieRemoteDataSource.fetchMovieSearch(query, API_KEY)
    }
}