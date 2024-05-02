package com.example.one.nav

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.helper.LocalDpHelper
import com.example.one.myui.ComprehensiveHotMap
import com.example.one.myui.Player
import com.example.one.page.AnalysePage
import com.example.one.page.OthersPage
import com.example.one.page.StorePage
import com.example.one.page.CharRiverPage
import com.example.one.vm.HotMapViewModel
import com.example.one.vm.MyHotMapViewModelFactory
import kotlinx.coroutines.launch

/**
 * 侧边栏与底部栏集成navigation
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController)
{
    Log.d("refresh","MainScreen Refresh")
    val hotMapViewModel: HotMapViewModel = viewModel(
        LocalContext.current as ComponentActivity
        ,factory = MyHotMapViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val menuItems = listOf(
        NavigationMenuScreen.MainPage,
        NavigationMenuScreen.CharRiverPage,
        NavigationMenuScreen.OthersPage,
        NavigationMenuScreen.StorePage,
        NavigationMenuScreen.AnalysePage
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
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
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
                HorizontalDivider(modifier = Modifier.padding(5.dp), thickness = 2.dp)
                Text(text = "每月活力",modifier = Modifier.padding(20.dp))
                ComprehensiveHotMap(vm = hotMapViewModel)

            }
        },
    ) {
        // 竖屏方案
        if(LocalDpHelper.getDpHeight() > LocalDpHelper.getDpWidth())
        {
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
                                },
                                    colors =
                                    if(it.route == currentRoute)
                                        IconButtonDefaults.iconButtonColors()
                                    else
                                        IconButtonDefaults.iconButtonColors()
                                ) {
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
        }else{
            Scaffold(
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        text = { Text("Show drawer") },
                        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
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
}
/**
 * 路由表
 */
@Composable
fun Navigation(navController: NavHostController)
{
    NavHost(navController = navController, startDestination = "MainPage" )
    {
        composable("MainPage"){
            MainPage()
        }
        composable("CharRiverPage")
        {
            CharRiverPage()
        }
        composable("OthersPage")
        {
            OthersPage()
        }
        composable("StorePage")
        {
            StorePage()
        }
        composable("AnalysePage")
        {
            AnalysePage()
        }
    }
}