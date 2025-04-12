package com.example.moviedatabase.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviedatabase.data.local.Watchlist
import com.example.moviedatabase.data.viewModel.WatchlistViewModel
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem
import androidx.compose.foundation.lazy.items

@Composable
fun LibraryScreen(bottomNavItems: List<bottomNavItem>,
                  currentDestination: String?,
                  viewModel: WatchlistViewModel = viewModel()
){
    val Watchlists by viewModel.watchlist.collectAsStateWithLifecycle()

    Scaffold (
        topBar = { },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            currentRoute = currentDestination
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.createWatchlist("test")
                }
            ) {
                Icon(Icons.Filled.Add, "add list")
            }
        },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->

        LibraryContent(
            modifier = Modifier.padding(innerPadding),
            watchlist = Watchlists
        )
    }
}

@Composable
fun LibraryContent(
    modifier: Modifier = Modifier,
    watchlist: List<Watchlist>,

){

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(watchlist) { item ->
            WatchListItem(
                item = item,
                modifier = modifier
            )
        }
    }
}

@Composable
fun WatchListItem(item: Watchlist,
    modifier: Modifier = Modifier,
) {
    Row (
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(item.name)
        }
    }
}