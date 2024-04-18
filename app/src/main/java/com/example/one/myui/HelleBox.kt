package com.example.one.myui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.one.helper.getTimeScr
import com.example.one.ui.theme.ONETheme

@Composable
fun HelloBox(string: String? = null){
    Column {
        Text(text = "Hi!", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = string ?: (getTimeScr() + "好"), fontSize = 50.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun TestHelloBox(){
    ONETheme {
        Column {
            HelloBox(string = "正在专注")
            Spacer(modifier = Modifier.height(10.dp))
            HelloBox()
        }
    }
}