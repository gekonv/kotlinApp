package com.example.moviedatabase.data.datasource

import com.example.moviedatabase.data.local.Genre
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.remote.ApiCallResult
import com.example.moviedatabase.data.remote.GenreApi
import com.example.moviedatabase.data.remote.MovieApi
import com.example.moviedatabase.data.remote.WebApi
import java.io.IOException

class MovieRemoteDataSource(
    private val webApi: WebApi
){
    suspend fun fetchPopularMovies(apiKey: String): ApiCallResult<List<Movie>> {
        val response = webApi.getPopularMovies(apiKey)
        return if (response.isSuccessful) {
            response.body()?.let { movieApiCollection ->
                val movieList = movieApiCollection.results.map { results ->
                    results.toMovie()
                }
                ApiCallResult.Success(movieList)
            } ?: ApiCallResult.Error(IOException("Feature collection is null"))
        } else {
            ApiCallResult.Error(IOException("Failed to fetch movies from web"))
        }
    }

    suspend fun fetchNowPlayingMovies(apiKey: String): ApiCallResult<List<Movie>>{
        val response = webApi.getNowPlaying(apiKey)
        return if (response.isSuccessful) {
            response.body()?.let { movieApiCollection ->
                val movieList = movieApiCollection.results.map { results ->
                    results.toMovie()
                }
                ApiCallResult.Success(movieList)
            } ?: ApiCallResult.Error(IOException("Feature collection is null"))
        } else {
            ApiCallResult.Error(IOException("Failed to fetch movies from web"))
        }
    }

    suspend fun fetchUpcoming(apiKey: String): ApiCallResult<List<Movie>>{
        val response = webApi.getUpcoming(apiKey)
        return if (response.isSuccessful) {
            response.body()?.let { movieApiCollection ->
                val movieList = movieApiCollection.results.map { results ->
                    results.toMovie()
                }
                ApiCallResult.Success(movieList)
            } ?: ApiCallResult.Error(IOException("Feature collection is null"))
        } else {
            ApiCallResult.Error(IOException("Failed to fetch movies from web"))
        }
    }

    suspend fun fetchMovieDetail(id: Int, apiKey: String): ApiCallResult<Movie>{
        val response = webApi.getMovieDetail(id, apiKey)
        return if (response.isSuccessful) {
            response.body()?.let { movie ->
                ApiCallResult.Success(movie.toMovie())
            } ?: ApiCallResult.Error(IOException("Feature collection is null"))
        } else {
            ApiCallResult.Error(IOException("Failed to fetch movies from web"))
        }
    }

    suspend fun fetchMovieSearch(query: String, apiKey: String): ApiCallResult<List<Movie>>{
        val response = webApi.searchMovies(query, apiKey)
        return if (response.isSuccessful) {
            response.body()?.let { movieApiCollection ->
                val movieList = movieApiCollection.results.map { results ->
                    results.toMovie()
                }
                ApiCallResult.Success(movieList)
            } ?: ApiCallResult.Error(IOException("Feature collection is null"))
        } else {
            ApiCallResult.Error(IOException("Failed to fetch movies from web"))
        }
    }
}

private fun MovieApi.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
        genres = genres.map { it.toGenre() },
        overview = overview
    )
}

private fun GenreApi.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}