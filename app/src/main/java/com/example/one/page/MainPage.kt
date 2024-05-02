package com.example.one.page

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.myui.StateBox
import com.example.one.myui.HotMap
import com.example.one.myui.MainPageController
import com.example.one.myui.MyTimer
import com.example.one.helper.LocalDpHelper
import com.example.one.setting.Setting
import com.example.one.vm.HotMapViewModel
import com.example.one.vm.MyHotMapViewModelFactory
import com.example.one.vm.TimerViewModel

@Composable
fun MainPage()
{
    // 提升
    val timerViewModel: TimerViewModel = viewModel(LocalContext.current as ComponentActivity)
    val hotMapViewModel: HotMapViewModel = viewModel(LocalContext.current as ComponentActivity
        ,factory = MyHotMapViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    val mainPageState = hotMapViewModel.Type.observeAsState()

    if(LocalDpHelper.getDpHeight() > LocalDpHelper.getDpWidth())
    {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.weight(0.5f)) {
                    StateBox(mainPageState,Modifier)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.weight(9f))
                {
                    Column (modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        HotMap(hotMapViewModel,mainPageState,Modifier.weight(2.5f))
                        Spacer(modifier = Modifier.height(2.dp))
                        MyTimer(viewModel = timerViewModel,mainPageState,Modifier.weight(5f))
                        Spacer(modifier = Modifier.height(2.dp))
                        MainPageController(hotMapViewModel::changeType,Modifier.weight(1f))
                    }
                }
            
        }
    }else{
        Row {
            Column(modifier = Modifier.weight(0.5f)) {
                HotMap(vm = hotMapViewModel, mainPageState = mainPageState, modifier = Modifier.weight(4f))
                Spacer(modifier = Modifier.height(10.dp))
                MainPageController(changeType = hotMapViewModel::changeType, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(0.5f)) {
                MyTimer(viewModel = timerViewModel, mainPageState = mainPageState, modifier = Modifier)
            }
        }
    }

}