package com.example.moviedatabase.data.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.data.datasource.MovieRemoteDataSource
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.remote.ApiCallResult
import com.example.moviedatabase.data.remote.WebApi
import com.example.moviedatabase.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val movieRepository = MovieRepository(MovieRemoteDataSource(WebApi.getApiService()))

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()


    val searchText = MutableStateFlow("")
    init {
        observeSearch()
    }

    private fun observeSearch() {
        viewModelScope.launch {
            searchText
                .debounce(1000L)
                .distinctUntilChanged()
                .onEach { query ->
                    if (query.isBlank()) {
                        _movies.value = emptyList()
                        _isSearching.value = false
                    }
                }
                .filter { it.isNotBlank() }
                .onEach { _isSearching.value = true }
                .flatMapLatest { query ->
                    flow {
                        when (val result = movieRepository.fetchMovieSearch(query)) {
                            is ApiCallResult.Success -> emit(result.data)
                            is ApiCallResult.Error -> {
                                emit(emptyList())
                            }
                        }
                    }
                }
                .onEach { results ->
                    _movies.value = results
                    _isSearching.value = false
                }
                .catch { e ->
                    Log.e("SearchException", "Exception: ${e.message}")
                    _isSearching.value = false
                    _movies.value = emptyList()
                }
                .launchIn(this)
        }
    }
    fun onSearchTextChanged(newText: String) {
        searchText.value = newText
    }
}