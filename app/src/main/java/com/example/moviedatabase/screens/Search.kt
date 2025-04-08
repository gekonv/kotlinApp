package com.example.moviedatabase.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem

@Composable
fun SearchScreen(bottomNavItems: List<bottomNavItem>,
                 currentDestination: String?){
    Scaffold (
        topBar = {  },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            currentRoute = currentDestination
        ) },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->
        SearchContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@Composable
fun SearchContent(
    modifier: Modifier,
){
    Text(text = "SEARCH")
    Column {  }
}