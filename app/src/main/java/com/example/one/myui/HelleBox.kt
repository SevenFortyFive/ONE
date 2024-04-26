package com.example.one.myui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.one.helper.getTimeScr
import com.example.one.page.MainPageState
import com.example.one.ui.theme.ONETheme

@Composable
fun HelloBox(mainPageState:MutableState<MainPageState>,string: String? = null){
    Box(modifier = Modifier.fillMaxWidth())
    {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Hi!", fontSize = 15.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier =Modifier.fillMaxWidth()) {
                Text(text = string ?: (getTimeScr() + "好"), fontSize = 25.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = when(mainPageState.value){
                    MainPageState.BREATH -> "正在专注呼吸"
                    MainPageState.MEDITATION -> "正在冥想"
                    MainPageState.CLOCK -> "正在专注"
                    MainPageState.DRINK -> "多多喝水"
                })
            }
        }
    }
}