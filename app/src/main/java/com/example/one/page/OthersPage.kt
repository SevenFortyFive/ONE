package com.example.one.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one.myui.SumTime
import com.example.one.ui.theme.ONETheme

@Composable
fun OthersPage()
{
    Column {
//        SumTime()
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TestOtherPage(){
    ONETheme {
        OthersPage()
    }
}