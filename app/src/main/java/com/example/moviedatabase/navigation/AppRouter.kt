package com.example.moviedatabase.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviedatabase.R
import com.example.moviedatabase.screens.HomeScreen
import com.example.moviedatabase.screens.LibraryScreen
import com.example.moviedatabase.screens.MovieDetailScreen
import com.example.moviedatabase.screens.SearchScreen
import com.example.moviedatabase.screens.WatchlistContentScreen


@Composable
fun AppRouter() {
    val navController = rememberNavController()

    MainAppRouter(
        navController = navController,
    )
}

@Composable
fun MainAppRouter(navController: NavHostController){
    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    val bottomNavItems = remember {
        listOf(
            bottomNavItem(
                route = Routes.Search::class.qualifiedName.toString(),
                icon = R.drawable.search,
                label = "Search",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.Search)
                },
                contentDescription = "search screen"
            ),
            bottomNavItem(
                route = Routes.Home::class.qualifiedName.toString(),
                icon = R.drawable.home,
                label = "Home",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.Home)
                },
                contentDescription = "screen with best rated movies",
            ),
            bottomNavItem(
                route = Routes.Library::class.qualifiedName.toString(),
                icon = R.drawable.library,
                label = "Library",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.Library)
                },
                contentDescription = "screen with my library"
            )
        )
    }
    NavHost(navController = navController, startDestination = Routes.Home){
        composable<Routes.Home>(){
            HomeScreen(
                bottomNavItems,
                currentBackStackEntry.value?.destination?.route,
                onItemClick = {
                    navController.navigate(Routes.MovieDetail(it))
                }
            )
        }
        composable<Routes.Library>(){
            LibraryScreen(
                bottomNavItems,
                currentBackStackEntry.value?.destination?.route,
                onItemClick = {
                    navController.navigate(Routes.WatchlistContent(it))
                }
            )
        }
        composable<Routes.Search>(){
            SearchScreen(
                bottomNavItems,
                currentBackStackEntry.value?.destination?.route,
                onItemClick = {
                    navController.navigate(Routes.MovieDetail(it))
                }
            )
        }
        composable<Routes.MovieDetail> {
            MovieDetailScreen(
                goBack = { navController.popBackStack() }
            )
        }
        composable<Routes.WatchlistContent> {
            WatchlistContentScreen(
                bottomNavItems = bottomNavItems,
                currentDestination = currentBackStackEntry.value?.destination?.route,
                goBack = {
                    navController.popBackStack()
                },
                onItemClick = {
                    navController.navigate(Routes.MovieDetail(it))
                }
            )
        }
    }
}

fun navigateToBottomNavItem(navController: NavHostController, route: Routes) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun BottomNavigation(
    navigationItems: List<bottomNavItem>,
    currentRoute: String?,
) {
    NavigationBar {
        navigationItems.forEach{ item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(item.icon),
                        contentDescription = item.contentDescription,
                    )
                },
                label = { Text(item.label)},
                selected = item.route == currentRoute,
                onClick = item.onClick,
            )
        }
    }
}