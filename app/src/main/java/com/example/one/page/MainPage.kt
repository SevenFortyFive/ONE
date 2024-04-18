package com.example.one.page

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.one.myui.TextCell

@Composable
fun MainPage()
{
    Row {
        TextCell(string = "MainPage")
        Spacer(modifier = Modifier.height(10.dp))

    }
}