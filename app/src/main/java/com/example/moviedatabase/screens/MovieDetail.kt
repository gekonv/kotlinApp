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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.moviedatabase.R
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.Routes
import com.example.moviedatabase.navigation.bottomNavItem

@Composable
fun MovieDetailScreen(
    bottomNavItems: List<bottomNavItem>,
    currentDestination: String?,
    backgroundUrl: String,
    posterUrl: String,
    title: String,
    genres: List<String>,
    releaseDate: String,
    duration: String,
    rating: String,
    goBack: () -> Unit,
    addToList: () -> Unit,
    synopsis: String
){
    Scaffold (
        topBar = { DetailTopBar(
            goBack = goBack,
            addToList = addToList
        ) },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            currentRoute = currentDestination
        ) },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->
        MovieDetailContent(
            modifier = Modifier.padding(innerPadding),
            backgroundUrl = backgroundUrl,
            posterUrl = posterUrl,
            title = title,
            genres = genres,
            releaseDate = releaseDate,
            duration = duration,
            rating = rating,
            synopsis = synopsis
        )
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
    backgroundUrl: String,
    posterUrl: String,
    title: String,
    genres: List<String>,
    releaseDate: String,
    duration: String,
    rating: String,
    synopsis: String
){
    Box(modifier = Modifier.fillMaxSize()) {

        // Background Image
        AsyncImage(
            model = backgroundUrl,
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )


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
                //poster
                AsyncImage(
                    model = posterUrl,
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .height(200.dp)
                        .width(140.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                //title
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                //genre
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    genres.take(4).forEach { genre ->
                        Genre(text = genre)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            //details
            item {
                MovieDetailsRow("Release Date", releaseDate)
                MovieDetailsRow("Duration", duration)
                MovieDetailsRow("Rating", " $rating⭐")
                Spacer(modifier = Modifier.height(20.dp))
            }
            //synopsis
            item {
                Text(
                    text = "Synopsis",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = synopsis,
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
fun DetailTopBar(goBack: () -> Unit,
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
                    Icons.Outlined.Home,
                    contentDescription = "Navigation back",
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


@Preview(showBackground = true)
@Composable
fun PreviewMovieDetailScreen() {
    val sampleBottomNavItems = listOf(
        bottomNavItem(
            route = Routes.Search::class.qualifiedName.toString(),
            icon = R.drawable.search,
            label = "Search",
            onClick = {
            },
            contentDescription = "search screen"
        ),
        bottomNavItem(
            route = Routes.Home::class.qualifiedName.toString(),
            icon = R.drawable.home,
            label = "Home",
            onClick = {
            },
            contentDescription = "screen with best rated movies"
        ),
        bottomNavItem(
            route = Routes.Library::class.qualifiedName.toString(),
            icon = R.drawable.library,
            label = "Library",
            onClick = {
            },
            contentDescription = "screen with my library"
        )
    )
    MovieDetailScreen(bottomNavItems = sampleBottomNavItems,
        currentDestination = rememberNavController().currentBackStackEntryAsState().value?.destination?.route,
        backgroundUrl = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
        posterUrl = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
        title = "Deadpool & Wolverine",
        genres = listOf("action", "drama"),
        releaseDate = "24.07.2000",
        duration = "128 min",
        rating = "8/10",
        goBack = {},
        addToList = {},
        synopsis = "'Avengers Assemble' ('The Avengers') is a truly enjoyable superhero film that lives up to its hype and creates a story that allows for four of the greatest superheroes to connect in this mega-blockbuster extravaganza. Joss Whedon has created one of the most action-packed Marvel films to have graced the screen, full of humour, thrills and a great cast of characters, all of which impel this visual effects-driven spectacle. Whilst I had the great opportunity to watch this epic in the cinema in 3D, the film is equally as stunning on an average television set, with the final battle between the Avengers and Loki's army being one of the most spectacular scenes in a superhero movie. An impressive and remarkable fantastical superhero flick from Whedon." +
                "'Avengers Assemble' ('The Avengers') is a truly enjoyable superhero film that lives up to its hype and creates a story that allows for four of the greatest superheroes to connect in this mega-blockbuster extravaganza. Joss Whedon has created one of the most action-packed Marvel films to have graced the screen, full of humour, thrills and a great cast of characters, all of which impel this visual effects-driven spectacle. Whilst I had the great opportunity to watch this epic in the cinema in 3D, the film is equally as stunning on an average television set, with the final battle between the Avengers and Loki's army being one of the most spectacular scenes in a superhero movie. An impressive and remarkable fantastical superhero flick from Whedon."
        )
}

