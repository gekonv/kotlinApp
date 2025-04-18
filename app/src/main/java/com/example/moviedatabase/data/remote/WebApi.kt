package com.example.moviedatabase.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

val json = Json {
    ignoreUnknownKeys = true
//    isLenient = true
//    coerceInputValues = true
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

interface WebApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("api_key") apiKey: String): MovieResponse

    companion object{
        @Volatile
        private var INSTANCE: WebApi? = null

        fun getApiService(): WebApi{
            return INSTANCE ?: synchronized(this){
                val instance = retrofit.create(WebApi::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}