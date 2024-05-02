package com.example.one.myui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Legend(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Column {
            Row {
                Cell(value = 0)
                Cell(value = 5)
                Cell(value = 10)
            }
            Row {
                TextCell(string = "少")
                EmptyCell()
                TextCell(string = "多")
            }
        }
    }
}