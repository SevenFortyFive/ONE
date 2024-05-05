package com.example.one.myui

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.setting.Setting
import com.example.one.vm.SPViewModel

@Composable
fun SumTime(){
    val vm:SPViewModel = viewModel(LocalContext.current as ComponentActivity)
    val breathTime by vm.breathTime.observeAsState()
    val clockTime by vm.clockTime.observeAsState()
    val drinkTime by vm.drinkTime.observeAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(10.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(Setting.WholeElevation)
    ) {
        Column(modifier = Modifier.padding(20.dp)
            ) {
            Text(text = "今日", modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
                ) {
                SmallSumTime("drink",breathTime)
                Spacer(modifier = Modifier.width(20.dp))
                SmallSumTime("clock",clockTime)
                Spacer(modifier = Modifier.width(20.dp))
                SmallSumTime("drink",drinkTime)
            }
        }
    }
}
@Composable
fun SmallSumTime(type:String,value: Int?) {
    Card(
        modifier =Modifier
        .width(80.dp)
        .animateContentSize()
        .padding(10.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp)) {
        Column {
            Text(text = type)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = value.toString())
        }
    }
}