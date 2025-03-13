package com.example.moviedatabase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviedatabase.BottomNavigation
import com.example.moviedatabase.Movie
import com.example.moviedatabase.bottomNavItem
import com.example.moviedatabase.movies

@Composable
fun HomeScreen(bottomNavItems: List<bottomNavItem>,
               selectedItemIndex: Int){
    Scaffold (
        topBar = {  },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            selectedItemIndex = selectedItemIndex
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
){
    Column {
        LazyRow {
            items(movies) { movie ->
                MovieItem(movie)

            }
        }
        LazyRow {
            items(movies) { movie ->
                MovieItem(movie)

            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No Image", color = MaterialTheme.colorScheme.onSecondaryContainer)
            }

            Column {
                Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
                Text(text = "Rating: ${movie.vote}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}