package com.example.one.nav

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.one.data.NavData.NavigationMenuScreen
import com.example.one.page.MainPage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.one.myui.Player
import com.example.one.page.OthersPage
import com.example.one.page.StorePage
import com.example.one.page.TodoPage
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController)
{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val menuItems = listOf(
        NavigationMenuScreen.MainPage,
        NavigationMenuScreen.Analyse,
        NavigationMenuScreen.Others,
        NavigationMenuScreen.Store
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        // 打开时启用手势
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            BackHandler(drawerState.isOpen) {
                scope.launch { drawerState.close() }
            }
            ModalDrawerSheet {
                Text(text = "导航",modifier = Modifier.padding(20.dp))
                menuItems.forEach{
                    NavigationDrawerItem(label = {
                        Text(text = it.title)
                    },
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

                HorizontalDivider(modifier = Modifier.padding(5.dp), thickness = 2.dp)
                Text(text = "音乐控制器",modifier = Modifier.padding(20.dp))
                Player()
            }
        },
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        menuItems.forEach{
                            IconButton(onClick = {
                                if(currentRoute != it.route)
                                {
                                    navController.navigate(it.route)
                                }
                            }) {
                                Icon(it.icon, contentDescription = "")
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            } },
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "Localized description")
                        }
                    }
                )
            }
        ) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                Navigation(navController = navController)
            }
        }
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
        composable("Store")
        {
            StorePage()
        }
    }
}