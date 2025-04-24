package com.example.moviedatabase.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.moviedatabase.R
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.viewModel.WatchlistContentViewModel
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem

@Composable
fun WatchlistContentScreen(
    goBack: () -> Unit,
    bottomNavItems: List<bottomNavItem>,
    currentDestination: String?,
    viewModel: WatchlistContentViewModel = viewModel(),
    onItemClick: (Int) -> Unit
){
    val watchlistName by viewModel.watchlistName.collectAsState()
    val movies by viewModel.movies.collectAsState()

    Scaffold (
        topBar = { WatchlistContentTopBar(
            goBack = goBack,
            watchlistName
        ) },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            currentRoute = currentDestination
        ) },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->
        WatchlistContentContent(
            modifier = Modifier.padding(innerPadding),
            onItemClick = onItemClick,
            movies = movies,
            deleteMovie = { movie ->
                viewModel.deleteMovie(movie)
            }
        )
    }
}

@Composable
fun WatchlistContentContent(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onItemClick: (Int) -> Unit,
    deleteMovie: (Movie) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies.size) { index ->
            val movie = movies[index]
            WatchlistCard(
                movie = movie,
                onClick = onItemClick,
                delete = {
                    deleteMovie(movie)
                }
            )
        }
    }
}

@Composable
fun WatchlistCard(
    movie: Movie,
    onClick: (Int) -> Unit,
    delete: () -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        onClick = {
            onClick(movie.id)
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = "Poster",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = movie.releaseDate,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            IconButton(
                onClick = delete
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistContentTopBar(
    goBack: () -> Unit,
    name: String
){
    TopAppBar(
        title = {
            Text(text = "Watchlist: $name")

        },
        navigationIcon = {
            IconButton(
                onClick = goBack
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = "Navigation back",
                    modifier = Modifier.size(20.dp),
                )
            }
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = "Add to list",
                )
            }
        }
    )
}