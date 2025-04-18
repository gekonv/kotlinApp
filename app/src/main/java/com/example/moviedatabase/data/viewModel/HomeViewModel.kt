package com.example.moviedatabase.data.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.remote.Movie
import com.example.moviedatabase.data.remote.WebApi
import com.example.moviedatabase.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
    private val movieRepository = MovieRepository(MovieRemoteDataSource(WebApi.getApiService()))
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            _movies.value = movieRepository.fetchPopularMovies()
        }
    }
}