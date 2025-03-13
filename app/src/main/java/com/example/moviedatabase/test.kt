package com.example.moviedatabase

data class Movie(
    val id: Long = 0,
    val title: String = "",
    val vote: Double = 0.0,
    val imageUrl: String = "",
)

val movies : List<Movie> = listOf(
    Movie(
        id = 101,
        title = "Sídliště Petrovice - Rezlerova",
        vote = 4.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 102,
        title = "Sídliště Petrovice - Rezlerov",
        vote = 6.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1013,
        title = "Sídliště Petrovice - Rezlero",
        vote = 7.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1014,
        title = "Sídliště Petrovice - Rezle",
        vote = 8.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1015,
        title = "Sídliště Petrovice - Rez",
        vote = 9.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1016,
        title = "Sídliště Petrovice - R",
        vote = 10.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1017,
        title = "Sídliště Petrovice -",
        vote = 1.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1018,
        title = "Sídliště Petrovice",
        vote = 2.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1019,
        title = "Sídliště Petrov",
        vote = 3.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    ),
    Movie(
        id = 1020,
        title = "Sídliště Pet",
        vote = 41.5,
        imageUrl = "http://www.hristepraha.cz/images/img/41f5da50efa25a661c878386afd9fe27o.jpg"
    )
)