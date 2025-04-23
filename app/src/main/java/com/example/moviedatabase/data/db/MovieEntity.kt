package com.example.moviedatabase.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.moviedatabase.data.db.WatchlistEntity
import com.example.moviedatabase.data.local.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(
    tableName = "movies",
    foreignKeys = [
        ForeignKey(
            entity = WatchlistEntity::class,
            parentColumns = ["listId"],
            childColumns = ["listId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ]
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Int = 0,
    val listId: Long = 0,
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val runtime: Int = 0,
    val voteAverage: Double = 0.0,
    val genres: String = "",
    val overview: String = ""
)

class GenreConverter {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genreString: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(genreString, type)
    }
}