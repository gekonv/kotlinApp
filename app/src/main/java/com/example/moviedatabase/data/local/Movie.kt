package com.example.moviedatabase.data.local

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val runtime: Int = 0,
    val voteAverage: Double = 0.0,
    val genres: List<Genre> = emptyList(),
    val overview: String = ""
)

data class Genre(
    val id: Int,
    val name: String
)