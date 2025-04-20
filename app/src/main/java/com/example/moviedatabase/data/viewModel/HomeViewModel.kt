package com.example.moviedatabase.data.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.remote.ApiCallResult
import com.example.moviedatabase.data.remote.WebApi
import com.example.moviedatabase.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
    private val movieRepository = MovieRepository(MovieRemoteDataSource(WebApi.getApiService()))

    private val _popularList = MutableStateFlow<List<Movie>>(emptyList())
    val popularList : StateFlow<List<Movie>> = _popularList.asStateFlow()

    private val _nowPlayingList = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingList : StateFlow<List<Movie>> = _nowPlayingList.asStateFlow()

    private val _upcomingList = MutableStateFlow<List<Movie>>(emptyList())
    val upcomingList : StateFlow<List<Movie>> = _upcomingList.asStateFlow()

    init {
        viewModelScope.launch {
            val result = movieRepository.fetchPopularMovies()
            if(result is ApiCallResult.Success){
                _popularList.value = result.data
            }

            val result1 = movieRepository.fetchNowPlayingMovies()
            if(result1 is ApiCallResult.Success){
                _nowPlayingList.value = result1.data
            }

            val result2 = movieRepository.fetchUpcoming()
            if(result2 is ApiCallResult.Success){
                _upcomingList.value = result2.data
            }
        }
    }

}