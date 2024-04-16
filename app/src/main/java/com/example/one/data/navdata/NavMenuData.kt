package com.example.one.data.navdata

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuScreen (
    val route: String,
    val icon: ImageVector,
    val title: String
){
    data object MainPage : BottomMenuScreen("MainPage", Icons.Default.Home,"主页")
    data object HotMapPage : BottomMenuScreen("BreathePage", Icons.Default.Check,"breathe")
    data object Analyse : BottomMenuScreen("ClockPage", Icons.Default.DateRange,"clock")
    data object Others : BottomMenuScreen("OthersPage", Icons.Default.Menu,"others")
}