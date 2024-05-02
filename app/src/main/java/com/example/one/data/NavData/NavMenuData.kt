package com.example.one.data.NavData

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
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
    data object CharRiverPage : NavigationMenuScreen("CharRiverPage", Icons.Default.DateRange,"词牌")
    data object OthersPage : NavigationMenuScreen("OthersPage", Icons.Default.Menu,"用户")
    data object StorePage:NavigationMenuScreen("StorePage",Icons.Default.ShoppingCart,"商店")
    data object AnalysePage : NavigationMenuScreen("AnalysePage",Icons.Default.Edit,"分析")
}