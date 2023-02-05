package com.example.readingappjet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readingappjet.screens.ReaderSplashScreen
import com.example.readingappjet.screens.details.BookDetailsScreen
import com.example.readingappjet.screens.home.ReaderHomeScreen
import com.example.readingappjet.screens.login.ReaderLoginScreen
import com.example.readingappjet.screens.search.ReaderBookSearchScreen
import com.example.readingappjet.screens.stats.ReaderStatsScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ReaderScreens.SplashScreen.name){
        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController = navController)
        }
        composable(ReaderScreens.LoginScreen.name){
            ReaderLoginScreen(navController = navController)
        }
        composable(ReaderScreens.SearchScreen.name){
            ReaderBookSearchScreen(navController = navController)
        }
        composable(ReaderScreens.DetailScreen.name){
            BookDetailsScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderStatsScreen.name){
            ReaderStatsScreen(navController = navController)
        }

    }
}