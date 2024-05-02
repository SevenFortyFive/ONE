package com.example.one.myui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.one.data.MainPageState
import com.example.one.helper.LocalDpHelper
import com.example.one.helper.getTimeScr

@Composable
fun StateBox(mainPageState: State<MainPageState?>, modifier: Modifier){
    Box(modifier = modifier
        .fillMaxWidth())
    {
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Row(modifier = Modifier.fillMaxHeight()) {
                Text(text = "Hi !", fontSize = 20.sp, modifier = Modifier)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = (getTimeScr() + "好"), fontSize = 15.sp)
            }
            Text(
                text = when(mainPageState.value){
                MainPageState.BREATH -> "正在专注呼吸"
                MainPageState.MEDITATION -> "正在冥想"
                MainPageState.CLOCK -> "正在专注"
                MainPageState.DRINK -> "多多喝水"
                    null -> "Error"
                }
                , fontSize = 15.sp,
                modifier = Modifier.fillMaxHeight(),
                maxLines = 1
            )
        }
    }
}