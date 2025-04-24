package com.example.moviedatabase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import com.example.moviedatabase.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.local.Watchlist
import com.example.moviedatabase.data.viewModel.MovieDetailViewModel



@Composable
fun MovieDetailScreen(
    goBack: () -> Unit,
    viewModel: MovieDetailViewModel = viewModel(),
){
    val movie by viewModel.movie.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val watchlists by viewModel.watchlist.collectAsStateWithLifecycle()
    val selectedWatchlists = remember { mutableStateMapOf<Long, Boolean>() }

    Scaffold (
        topBar = { MovieDetailTopBar(
            goBack = goBack,
            addToList = { showDialog = true }
        ) },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->
        MovieDetailContent(
            modifier = Modifier.padding(innerPadding),
            movie = movie
        )
        if (showDialog) {
            AddToWatchlistDialog(
                watchlists = watchlists,
                selectedWatchlists = selectedWatchlists,
                onDismiss = { showDialog = false },
                onAdd = {
                    val selectedIds = selectedWatchlists.filterValues { it }.keys
                    viewModel.addMovieToWatchlists(movie, selectedIds)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun Genre(text: String) {
    Box(
        modifier = Modifier
            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun MovieDetailsRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Light)
        Text(text = value, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun MovieDetailContent(
    modifier: Modifier,
    movie: Movie
){
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)

        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .height(200.dp)
                        .width(140.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movie.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val genres = movie.genres.map { it.name }
                    genres.take(4).forEach { genre ->
                        Genre(text = genre)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                MovieDetailsRow("Release Date", movie.releaseDate)
                MovieDetailsRow("Duration", movie.runtime.toString() + " mins")
                MovieDetailsRow("Rating", movie.voteAverage.toString() + " / 10")
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Text(
                    text = "Synopsis",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = movie.overview,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailTopBar(goBack: () -> Unit,
                 addToList: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = "Movie details")

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
                onClick = addToList
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = "Add to list",
                )
            }
        }
    )
}

@Composable
fun AddToWatchlistDialog(
    watchlists: List<Watchlist>,
    selectedWatchlists: MutableMap<Long, Boolean>,
    onDismiss: () -> Unit,
    onAdd: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add to Watchlists") },
        text = {
            LazyColumn {
                items(watchlists.size) { index ->
                    val watchlist = watchlists[index]
                    val isSelected = selectedWatchlists[watchlist.listId] ?: false
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        androidx.compose.material3.Checkbox(
                            checked = isSelected,
                            onCheckedChange = { selected ->
                                selectedWatchlists[watchlist.listId] = selected
                            }
                        )
                        Text(watchlist.name)
                    }
                }
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onAdd) {
                Text("Add")
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
