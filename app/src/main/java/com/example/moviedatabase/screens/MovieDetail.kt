package com.example.moviedatabase.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviedatabase.BottomNavigation
import com.example.moviedatabase.bottomNavItem

@Composable
fun MovieDetailScreen(bottomNavItems: List<bottomNavItem>,
                  selectedItemIndex: Int){
    Scaffold (
        topBar = {  },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            selectedItemIndex = selectedItemIndex
        ) },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->
        MovieDetailContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MovieDetailContent(
    modifier: Modifier,
){
    Text(text = "MovieDetailContent")
    Column {   }
}