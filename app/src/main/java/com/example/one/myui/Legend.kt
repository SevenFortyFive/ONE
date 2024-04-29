package com.example.one.myui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one.ui.theme.ONETheme

@Composable
fun Legend(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Column {
            Row {
                CellInCharRiver(value = 0)
                CellInCharRiver(value = 5)
                CellInCharRiver(value = 10)
            }
            Row {
                TextCell(string = "少")
                EmptyCell()
                TextCell(string = "多")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestLegend(){
    ONETheme {
        Legend()
    }
}