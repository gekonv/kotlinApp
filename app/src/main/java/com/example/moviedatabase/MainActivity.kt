package com.example.moviedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviedatabase.screens.HomeScreen
import com.example.moviedatabase.screens.LibraryScreen
import com.example.moviedatabase.screens.SearchScreen
import com.example.moviedatabase.ui.theme.MovieDataBaseTheme
import kotlin.collections.forEach

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDataBaseTheme {
                MainScreen()
            }
        }
    }
}

data class bottomNavItem(
    val icon: Int,
    val label: String,
    val onClick: () -> Unit,
    val contentDescription: String
)

@Composable
@Preview
fun MainScreen(){
    var selectedItem by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        bottomNavItem(
            icon = R.drawable.search,
            label = "Search",
            onClick = {
                navController.navigate(Routes.Search.route){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                selectedItem = 2
            },
            contentDescription = "search screen"
        ),
        bottomNavItem(
            icon = R.drawable.home,
            label = "Home",
            onClick = {
                navController.navigate(Routes.Home.route){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                selectedItem = 1
            },
            contentDescription = "screen with best rated movies"
        ),
        bottomNavItem(
            icon = R.drawable.library,
            label = "Library",
            onClick = {
                navController.navigate(Routes.Library.route){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                selectedItem = 0
            },
            contentDescription = "screen with my library"
        )
    )
    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(Routes.Home.route){
            HomeScreen(bottomNavItems = bottomNavItems, selectedItemIndex = selectedItem)
        }
        composable(Routes.Library.route){
            LibraryScreen(selectedItemIndex = selectedItem, bottomNavItems = bottomNavItems)
        }
        composable(Routes.Search.route){
            SearchScreen(selectedItemIndex = selectedItem, bottomNavItems = bottomNavItems)
        }
    }
}

sealed class Routes(val route: String){
    data object Home: Routes("Home")
    data object Library: Routes("Library")
    data object Search: Routes("Search")
}

@Composable
fun BottomNavigation(
    navigationItems: List<bottomNavItem>,
    selectedItemIndex: Int
) {
    NavigationBar {
        navigationItems.forEach{
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(it.icon),
                        contentDescription = it.contentDescription,


                        )
                },
                label = { Text(it.label)},
                selected = selectedItemIndex == 0,
                onClick = it.onClick,
            )
        }
    }
}