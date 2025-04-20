package com.example.moviedatabase.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String){
    @Serializable
    data object Home: Routes("Home")
    @Serializable
    data object Library: Routes("Library")
    @Serializable
    data object Search: Routes("Search")
    @Serializable
    data class MovieDetail(val id: Int): Routes("MovieDetail")
}