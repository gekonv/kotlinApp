package com.example.moviedatabase.data.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.remote.ApiCallResult
import com.example.moviedatabase.data.remote.WebApi
import com.example.moviedatabase.data.repository.MovieRepository
import com.example.moviedatabase.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MovieDetailViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val route : Routes.MovieDetail = savedStateHandle.toRoute()
    private val id = route.id

    private val movieRepository = MovieRepository(MovieRemoteDataSource(WebApi.getApiService()))

    private val _movie = MutableStateFlow(Movie())
    val movie = _movie.asStateFlow()

    init {
        viewModelScope.launch {
            val result = movieRepository.fetchMovieDetail(id)
            if(result is ApiCallResult.Success){
                _movie.value = result.data
            }
        }
    }

}