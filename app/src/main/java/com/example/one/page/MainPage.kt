package com.example.one.page

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.myui.HelloBox
import com.example.one.myui.HotMap
import com.example.one.myui.MainPageController
import com.example.one.myui.MyTimer
import com.example.one.ui.theme.ONETheme
import com.example.one.vm.HotMapViewModel
import com.example.one.vm.MyHotMapViewModelFactory
import com.example.one.vm.TimerViewModel


enum class MainPageState{
    BREATH,
    CLOCK,
    DRINK,
    MEDITATION
}

@Composable
fun MainPage()
{
    val mainPageState = remember {
        mutableStateOf(MainPageState.BREATH)
    }
    // 提升
    val timerViewModel: TimerViewModel = viewModel(LocalContext.current as ComponentActivity)
    val hotMapViewModel: HotMapViewModel = viewModel(LocalContext.current as ComponentActivity
        ,factory = MyHotMapViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            HelloBox(mainPageState)
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxSize())
            {
                Column (modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    HotMap(hotMapViewModel,mainPageState)
                    Spacer(modifier = Modifier.height(10.dp))
                    MyTimer(viewModel = timerViewModel,mainPageState)
                    Spacer(modifier = Modifier.height(10.dp))
                    MainPageController(mainPageState)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestMainPage()
{
    ONETheme {
        MainPage()
    }
}