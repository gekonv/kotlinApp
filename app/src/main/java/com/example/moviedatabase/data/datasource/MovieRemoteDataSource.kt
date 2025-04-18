package com.example.moviedatabase.data.datasource

import com.example.moviedatabase.data.remote.Movie
import com.example.moviedatabase.data.remote.WebApi
import java.io.IOException

class MovieRemoteDataSource(
    private val webApi: WebApi
){
    suspend fun fetchPopularMovies(apiKey: String): List<Movie> {
        return webApi.getPopularMovies(apiKey).results
    }
}
