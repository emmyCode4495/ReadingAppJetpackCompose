package com.example.readingappjet.navigation

import com.example.readingappjet.screens.ReaderSplashScreen
import com.example.readingappjet.screens.home.ReaderHomeScreen
import com.example.readingappjet.screens.login.ReaderLoginScreen

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    ReaderHomeScreen,
    SearchScreen,
    DetailScreen,
    UpdateScreen,
    ReaderStatsScreen;

    companion object{
        fun fromRoute(route:String): ReaderScreens
        = when(route.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            ReaderHomeScreen.name -> ReaderHomeScreen
            SearchScreen.name -> SearchScreen
            DetailScreen.name -> DetailScreen
            UpdateScreen.name -> UpdateScreen
            ReaderStatsScreen.name ->ReaderStatsScreen

            null -> ReaderHomeScreen

            else -> throw IllegalArgumentException("Route $route is invalid")

        }
    }
}