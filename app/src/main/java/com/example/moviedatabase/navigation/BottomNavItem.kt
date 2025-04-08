package com.example.moviedatabase.navigation

data class bottomNavItem(
    val route: String,
    val icon: Int,
    val label: String,
    val onClick: () -> Unit,
    val contentDescription: String
)