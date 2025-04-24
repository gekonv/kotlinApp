package com.example.moviedatabase.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.viewModel.HomeViewModel
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem

@Composable
fun HomeScreen(
    bottomNavItems: List<bottomNavItem>,
    currentDestination: String?,
    onItemClick: (Int) -> Unit
){
    Scaffold (
        topBar = {  },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            currentRoute = currentDestination
        ) },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding),
            onClick = { onItemClick(it) }
        )
    }
}


@Composable
fun HomeContent(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel(),
    onClick: (Int) -> Unit
){
    val popular by viewModel.popularList.collectAsStateWithLifecycle()
    val nowPlaying by viewModel.nowPlayingList.collectAsStateWithLifecycle()
    val upcoming by viewModel.upcomingList.collectAsStateWithLifecycle()

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(16.dp)
    ) {
        MovieRowSection("Popular", popular, onClick)
        Spacer(modifier = Modifier.height(24.dp))
        MovieRowSection("Now Playing", nowPlaying, onClick)
        Spacer(modifier = Modifier.height(24.dp))
        MovieRowSection("Coming Soon", upcoming, onClick)
    }
}

@Composable
fun MovieRowSection(section: String, movies: List<Movie>, onClick: (Int) -> Unit) {
    Column {
        Text(text = section, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(movies) { movie ->
                MovieCard(
                    movie,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 12.dp)
            .clickable{
                onClick(movie.id)
            }
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.title,
            maxLines = 2,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
