package com.example.one.page

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one.myui.HelloBox
import com.example.one.myui.HotMap
import com.example.one.myui.Player
import com.example.one.myui.TestUI
import com.example.one.myui.TextCell
import com.example.one.ui.theme.ONETheme

@Composable
fun MainPage()
{
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            HelloBox()
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxSize())
            {
                Column (modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    HotMap()
                    Spacer(modifier = Modifier.height(10.dp))
                    TestUI()
                    Spacer(modifier = Modifier.height(10.dp))
                    Player()
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