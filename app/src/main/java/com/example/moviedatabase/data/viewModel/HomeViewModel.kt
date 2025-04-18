package com.example.moviedatabase.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.remote.Movie
import com.example.moviedatabase.data.remote.WebApi
import com.example.moviedatabase.data.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
    private val movieRepository = MovieRepository(MovieRemoteDataSource(WebApi.getApiService()))

    var popular by mutableStateOf<List<Movie>>(emptyList())
        private set
    var nowPlaying by mutableStateOf<List<Movie>>(emptyList())
        private set
    var upcoming by mutableStateOf<List<Movie>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            popular = movieRepository.fetchPopularMovies()
            nowPlaying = movieRepository.fetchNowPlayingMovies()
            upcoming = movieRepository.fetchUpcoming()

        }
    }

}