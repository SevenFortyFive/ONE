package com.example.one.data.NavData

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationMenuScreen (
    val route: String,
    val icon: ImageVector,
    val title: String
){
    data object MainPage : NavigationMenuScreen("MainPage", Icons.Default.Home,"主页")
    data object Analyse : NavigationMenuScreen("TodoPage", Icons.Default.DateRange,"代办")
    data object Others : NavigationMenuScreen("OthersPage", Icons.Default.Menu,"用户")
    data object Store:NavigationMenuScreen("Store",Icons.Default.ShoppingCart,"商店")
}