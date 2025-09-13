package com.midoriai.leaflogic.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.midoriai.leaflogic.ui.screens.ChatScreen
import com.midoriai.leaflogic.ui.screens.FoodTrackingScreen
import com.midoriai.leaflogic.ui.screens.HealthScreen
import com.midoriai.leaflogic.ui.screens.HomeScreen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object FoodTracking : BottomNavItem("food_tracking", "Food", Icons.Default.Restaurant)
    object Health : BottomNavItem("health", "Health", Icons.Default.Favorite)
    object Chat : BottomNavItem("chat", "LLM Chat", Icons.Default.Chat)
}

@Composable
fun LeafLogicNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.FoodTracking,
                    BottomNavItem.Health,
                    BottomNavItem.Chat
                )

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen()
            }
            composable(BottomNavItem.FoodTracking.route) {
                FoodTrackingScreen()
            }
            composable(BottomNavItem.Health.route) {
                HealthScreen()
            }
            composable(BottomNavItem.Chat.route) {
                ChatScreen()
            }
        }
    }
}