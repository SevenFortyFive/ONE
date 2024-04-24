package com.example.one.myui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.ui.theme.ONETheme
import com.example.one.vm.SPViewModel

@Composable
fun SumTime(){
    val vm:SPViewModel = viewModel()
    val breath_time by vm._breath_time.observeAsState()
    val clock_time by vm._clock_time.observeAsState()
    val drink_time by vm._drink_time.observeAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .animateContentSize()
            .padding(10.dp)) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "今日")
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                SmallSumTime("drink",breath_time)
                Spacer(modifier = Modifier.width(20.dp))
                SmallSumTime("clock",clock_time)
                Spacer(modifier = Modifier.width(20.dp))
                SmallSumTime("drink",drink_time)
            }
        }
    }
}
@Composable
fun SmallSumTime(type:String,value: Int?) {
    Card(modifier = Modifier
        .background(color = Color.Green)
        .fillMaxHeight()
        .size(100.dp)
        .height(200.dp)
        .animateContentSize()
        .padding(10.dp)) {
        Column {
            Text(text = type)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = value.toString())
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TestSumTime(){
    ONETheme {
        SumTime()
    }
}