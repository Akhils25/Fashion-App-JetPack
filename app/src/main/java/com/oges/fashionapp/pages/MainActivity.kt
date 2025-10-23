package com.oges.fashionapp.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oges.fashionapp.ui.theme.FashionAppTheme

sealed class BottomNavScreen(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavScreen("home", Icons.Default.Home, "Home")
    object Wishlist : BottomNavScreen("wishlist", Icons.Default.Favorite, "Wishlist")
    object Search : BottomNavScreen("search", Icons.Default.Search, "Search")
    object Setting : BottomNavScreen("setting", Icons.Default.Settings, "Setting")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FashionAppTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute != "SplashPage"

                Box(modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()) {

                    NavHost(
                        navController = navController,
                        startDestination = "SplashPage",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("SplashPage") { SplashPage(navController = navController) }
                        composable(BottomNavScreen.Home.route) { HomeScreen() }
                        composable(BottomNavScreen.Wishlist.route) { WishlistScreen() }
                        composable(BottomNavScreen.Search.route) { SearchScreen() }
                        composable(BottomNavScreen.Setting.route) { SettingScreen() }
                    }

                    if (showBottomBar) {
                        BottomAppBar(
                            containerColor = Color.White,
                            tonalElevation = 10.dp,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                        ) {
                            val items = listOf(
                                BottomNavScreen.Home,
                                BottomNavScreen.Wishlist,
                                BottomNavScreen.Search,
                                BottomNavScreen.Setting
                            )
                            items.forEachIndexed { index, screen ->
                                if (index == 2) Spacer(modifier = Modifier.weight(1f)) // Space for FAB
                                val selected = currentRoute == screen.route
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = screen.label,
                                            tint = if (selected) Color.Red else Color.Black
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = screen.label,
                                            fontSize = 12.sp,
                                            color = if (selected) Color.Red else Color.Black
                                        )
                                    },
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }

                        FloatingActionButton(
                            onClick = { /* TODO */ },
                            containerColor = Color.White,
                            contentColor = Color.Black,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = (-28).dp)
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "FAB")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Wishlist,
        BottomNavScreen.Search,
        BottomNavScreen.Setting
    )

    BottomAppBar(
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, screen ->
            if (index == 2) Spacer(modifier = Modifier.weight(1f))
            val selected = currentRoute == screen.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label,
                        tint = if (selected) Color.Red else Color.Black
                    )
                },
                label = {
                    Text(
                        text = screen.label,
                        fontSize = 12.sp,
                        color = if (selected) Color.Red else Color.Black
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
fun HomeScreen() {
    Text("Home Screen")
}

@Composable
fun WishlistScreen() {
    Text("Wishlist Screen")
}

@Composable
fun SearchScreen() {
    Text("Search Screen")
}

@Composable
fun SettingScreen() {
    Text("Setting Screen")
}
