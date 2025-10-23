package com.oges.fashionapp.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: open cart */ }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Home.route) { }
            composable(BottomNavScreen.Wishlist.route) { }
            composable(BottomNavScreen.Search.route) { }
            composable(BottomNavScreen.Setting.route) { }
        }
    }
}

