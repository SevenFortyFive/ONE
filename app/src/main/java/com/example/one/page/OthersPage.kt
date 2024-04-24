package com.example.one.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one.myui.Store
import com.example.one.myui.SumTime
import com.example.one.ui.theme.ONETheme

@Composable
fun OthersPage()
{
    Column {
        Text(text = "OtherPage")
        Spacer(modifier = Modifier.padding(10.dp))
        SumTime()
        Spacer(modifier = Modifier.height(10.dp))
        Store()
    }
}

@Preview(showBackground = true)
@Composable
fun TestOtherPage(){
    ONETheme {
        OthersPage()
    }
}