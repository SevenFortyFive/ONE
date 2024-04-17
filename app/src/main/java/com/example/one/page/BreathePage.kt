package com.example.one.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.one.myui.hotmap.HotMap
import com.example.one.myui.player.Player
import com.example.one.ui.theme.ONETheme

@Composable
fun BreathePage()
{
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(modifier = Modifier
            .fillMaxSize()) {
            HotMap()
            Text(text = "BreathePage")
            Player()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestBreathePage()
{
    ONETheme {
        BreathePage()
    }
}