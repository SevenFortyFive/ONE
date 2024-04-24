package com.example.one

import com.example.one.vm.PlayerViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.one.data.SharedPreferences.MySharedPreference
import com.example.one.nav.MainScreen
import com.example.one.player.ExoPlayerManager
import com.example.one.ui.theme.ONETheme
import com.example.one.vm.HotMapViewModel
import com.example.one.vm.MyHotMapViewModelFactory
import com.example.one.vm.SPViewModel
import com.example.one.vm.TimerViewModel

class MainActivity : ComponentActivity() {

    private val timerViewModel:TimerViewModel by viewModels()
    private val playerViewModel: PlayerViewModel by viewModels()
    private val hotMapViewModel:HotMapViewModel by viewModels{MyHotMapViewModelFactory(this.application)}
    private val spViewModel:SPViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ExoPlayerManager.initializeExoPlayer(this)
        MySharedPreference.initSharedPreferences(this)

        setContent {
            ONETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ONEApp()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerViewModel.animatorController.stop()
        ExoPlayerManager.releasePlayer()
    }
}

@Composable
fun ONEApp(){
    val navController = rememberNavController()
    MainScreen(navController = navController)
}