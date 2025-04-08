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
import com.example.moviedatabase.navigation.AppRouter
import com.example.moviedatabase.navigation.bottomNavItem
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
                AppRouter()
            }
        }
    }
}
