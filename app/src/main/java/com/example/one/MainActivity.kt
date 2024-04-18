package com.example.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.one.nav.MainScreen
import com.example.one.player.ExoPlayerManager
import com.example.one.ui.theme.ONETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ONETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 初始化播放器
                    ExoPlayerManager.initializeExoPlayer(LocalContext.current)
                    ONEApp()
                }
            }
        }
    }
}

@Composable
fun ONEApp(){
    val navController = rememberNavController()
    MainScreen(navController = navController)
}