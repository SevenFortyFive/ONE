package com.example.one.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.one.myui.HotMap
import com.example.one.myui.Player

@Composable
fun ClockPage()
{
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(modifier = Modifier
            .fillMaxSize()) {
            HotMap()
            Text(text = "ColockPage")
            Player()
        }
    }
}