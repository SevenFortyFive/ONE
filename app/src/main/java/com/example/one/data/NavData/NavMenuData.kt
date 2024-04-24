package com.example.one.data.NavData

import androidx.compose.material.icons.Icons
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
    data object Analyse : BottomMenuScreen("TodoPage", Icons.Default.DateRange,"代办")
    data object Others : BottomMenuScreen("OthersPage", Icons.Default.Menu,"用户")
}