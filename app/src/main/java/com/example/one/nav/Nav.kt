package com.example.one.nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.one.data.NavData.BottomMenuScreen
import com.example.one.page.MainPage
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.one.page.OthersPage
import com.example.one.page.TodoPage
import com.example.one.helper.LocalDpHelper


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController)
{
    Scaffold(bottomBar = {
        BottomMenu(navController = navController)
    }) {
        Navigation(navController = navController)
    }
}

@Composable
fun Navigation(navController: NavHostController)
{
    NavHost(navController = navController, startDestination = "MainPage" )
    {
        composable("MainPage"){
            MainPage()
        }
        composable("TodoPage")
        {
            TodoPage()
        }
        composable("OthersPage")
        {
            OthersPage()
        }
    }
}

@Composable
fun BottomMenu(navController: NavController) {
    val menuItems = listOf<BottomMenuScreen>(
        BottomMenuScreen.MainPage,
        BottomMenuScreen.Analyse,
        BottomMenuScreen.Others
    )
    BottomNavigation(modifier = Modifier.height((LocalDpHelper.getDpHeight()*0.06).dp),contentColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menuItems.forEach {
            BottomNavigationItem(
                label = { Text(text = it.title) },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == it.route,
                onClick = {
                    if(currentRoute != it.route)
                    {
                        navController.navigate(it.route)
                    }
                },
                icon = {
                    Icon(imageVector = it.icon,
                        contentDescription = it.title
                    )
                }
            )
        }
    }
}