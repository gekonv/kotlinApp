package com.example.moviedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moviedatabase.navigation.AppRouter
import com.example.moviedatabase.ui.theme.MovieDataBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDataBaseTheme {
                AppRouter()
            }
        }
        AppContainer.init(this)
    }
}
