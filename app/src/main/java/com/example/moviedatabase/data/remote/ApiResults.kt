package com.example.moviedatabase.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<MovieApi>
)

@Serializable
data class MovieApi(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    val runtime: Int = 0,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    val genres: List<GenreApi> = emptyList(),
    val overview: String
)

@Serializable
data class GenreApi(
    val id: Int,
    val name: String
)