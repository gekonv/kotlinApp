package com.example.moviedatabase.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.moviedatabase.data.db.WatchlistEntity

@Entity(
    tableName = "movies",
    foreignKeys = [
        ForeignKey(
            entity = WatchlistEntity::class,
            parentColumns = ["listId"],
            childColumns = ["watchlistId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ]
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val movieId: Long = 0,
    val watchlistId: Long,

)