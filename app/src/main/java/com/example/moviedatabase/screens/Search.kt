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
fun SearchScreen(bottomNavItems: List<bottomNavItem>,
                 selectedItemIndex: Int){
    Scaffold (
        topBar = {  },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            selectedItemIndex = selectedItemIndex
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