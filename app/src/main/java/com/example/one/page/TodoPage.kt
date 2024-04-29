package com.example.one.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.one.myui.CharRiver
import com.example.one.myui.MyTimer
import com.example.one.myui.Player

@Composable
fun TodoPage()
{
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(modifier = Modifier
            .fillMaxSize()) {
            CharRiver()
        }
        Player()
    }
}