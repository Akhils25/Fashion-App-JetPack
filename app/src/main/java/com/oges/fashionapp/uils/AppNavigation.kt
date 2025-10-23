package com.oges.fashionapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oges.fashionapp.pages.MainScreen
import com.oges.fashionapp.pages.SplashPage

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "SplashPage") {

        // Splash Screen
        composable("SplashPage") {
            SplashPage(navController)
        }

        // Main Screen with Bottom Nav
        composable("MainScreen") {
            MainScreen(navController)
        }
    }
}

