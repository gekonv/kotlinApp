package com.example.moviedatabase.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviedatabase.R
import com.example.moviedatabase.data.viewModel.WatchlistContentViewModel
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem

@Composable
fun WatchlistContentScreen(
    goBack: () -> Unit,
    bottomNavItems: List<bottomNavItem>,
    currentDestination: String?,
    viewModel: WatchlistContentViewModel = viewModel(),
){
    val watchlistName by viewModel.watchlistName.collectAsState()

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
        )
    }
}

@Composable
fun WatchlistContentContent(
    modifier: Modifier,
){
    Text("fw")
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