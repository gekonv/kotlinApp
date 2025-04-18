package com.example.moviedatabase.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviedatabase.data.viewModel.HomeViewModel
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem

@Composable
fun HomeScreen(bottomNavItems: List<bottomNavItem>,
               currentDestination: String?){
    Scaffold (
        topBar = {  },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            currentRoute = currentDestination
        ) },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun HomeContent(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel()
){
    val movies by viewModel.movies.collectAsState()

    LazyColumn {
        items(movies) { movie ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
