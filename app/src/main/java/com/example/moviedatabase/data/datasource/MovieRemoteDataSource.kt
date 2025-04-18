package com.example.moviedatabase.data.datasource

import com.example.moviedatabase.data.remote.Movie
import com.example.moviedatabase.data.remote.WebApi

class MovieRemoteDataSource(
    private val webApi: WebApi
){
    suspend fun fetchPopularMovies(apiKey: String): List<Movie> {
        return webApi.getPopularMovies(apiKey).results
    }

    suspend fun fetchNowPlayingMovies(apiKey: String): List<Movie>{
        return webApi.getNowPlaying(apiKey).results
    }

    suspend fun fetchUpcoming(apiKey: String): List<Movie>{
        return webApi.getUpcoming(apiKey).results
    }
}
