package com.example.moviedatabase

import android.content.Context
import com.example.moviedatabase.data.db.WatchlistDatabase

object AppContainer {
    lateinit var watchlistDatabase: WatchlistDatabase
        private set

    fun init(context: Context){
        watchlistDatabase = WatchlistDatabase.getDatabase(context)
    }
}