package com.example.one.myui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one.ui.theme.ONETheme

@Composable
fun Player(){
    Card(modifier = Modifier.size(100.dp,100.dp)) {

    }
}

@Composable
fun PlayerController()
{
    Column {
        Card( modifier = Modifier, elevation =  10.dp) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPlayer()
{
    ONETheme {
        Player()
    }
}