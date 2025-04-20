package com.example.moviedatabase.screens

import android.icu.number.Scale
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviedatabase.data.local.Movie
import com.example.moviedatabase.data.viewModel.SearchViewModel
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem


@Composable
fun SearchScreen(
    bottomNavItems: List<bottomNavItem>,
    currentDestination: String?,
    viewModel: SearchViewModel = viewModel(),
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
        SearchContent(
            modifier = Modifier.padding(innerPadding),
            onItemClick = onItemClick,
            viewModel = viewModel
        )
    }
}
@Composable
fun SearchContent(
    modifier: Modifier,
    onItemClick: (Int) -> Unit,
    viewModel: SearchViewModel
){
    val movies by viewModel.movies.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val searchQuery by viewModel.searchText.collectAsState()

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newText ->
                viewModel.onSearchTextChanged(newText)
            },
            label = { Text("Search movies") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if(isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(items = movies) { movie ->
                    SearchCard(
                        movie,
                        onClick = onItemClick
                    )
                }
            }
        }
    }
}

@Composable
fun SearchCard(
    movie: Movie,
    onClick: (Int) -> Unit
){
    Card(
        modifier = Modifier.padding(8.dp),
        onClick = {
            onClick(movie.id)
        },
    ) {
        Row(verticalAlignment = CenterVertically) {
            Card(Modifier.size(128.dp)) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                )
            }
            Column(
                Modifier
                    .padding(vertical = 14.dp, horizontal = 14.dp)
                    .weight(1f)
            ) {
                Text(
                    text = movie.title, maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = movie.overview, maxLines = 2,
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

