package com.example.one.myui

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.one.data.hotmapdata.entity.MyHotMapData
import com.example.one.vm.HotMapVM
import com.example.one.vm.MyHotMapViewModelFactory

@Composable
fun TestUI(){
    var vm:HotMapVM = viewModel(factory = MyHotMapViewModelFactory(LocalContext.current.applicationContext as Application))

    var info = vm.info.observeAsState()

    Box(modifier = Modifier.fillMaxWidth()
        .height(300.dp)){
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                vm.add()
            }) {
                Text(text = "添加数据")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { vm.getAllData()}) {
                Text(text = "获取数据")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = info.value as  String)
        }
    }
}