package com.example.one.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OthersPage()
{
    Column {
        Text(text = "OtherPage",modifier = Modifier.fillMaxSize())
    }
}