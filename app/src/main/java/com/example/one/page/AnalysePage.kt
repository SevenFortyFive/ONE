package com.example.one.page

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.helper.MessageHelper
import com.example.one.helper.Toaster
import com.example.one.vm.HotMapViewModel
import com.example.one.vm.MyHotMapViewModelFactory

@Composable
fun AnalysePage(){
    val hotMapViewModel: HotMapViewModel = viewModel(LocalContext.current as ComponentActivity
        ,factory = MyHotMapViewModelFactory(LocalContext.current.applicationContext as Application)
    )
    // 用于发送消息的context对象
    val context = LocalContext.current

    Column {
        Button(onClick = {hotMapViewModel.sendPareTextMessage(context,"泥嚎")}) {
            Text(text = "发送信息")
        }
        Button(onClick = {Toaster.showShortToaster(context,"泥嚎")}) {
            Text(text = "发送toast")
        }
        Button(onClick = {hotMapViewModel.sharDetailHotMapMessage(context)}) {
            Text(text = "分享信息")
        }
    }

}