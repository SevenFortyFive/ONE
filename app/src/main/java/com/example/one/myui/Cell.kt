package com.example.one.myui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.one.helper.getColorForValue
import com.example.one.setting.Setting

@Composable
fun CellInCharRiver(value: Int, isToday:Boolean? = false, string: String?=null)
{
    val color =  getColorForValue(value)
    val modifier = if(isToday == true)
    {
        Modifier
            .size(Setting.CellSize, Setting.CellSize)
            .clip(RoundedCornerShape(2.dp))
            .border(BorderStroke(width = 1.dp, color = Color.Green))
            .background(color)
    }
    else{
        Modifier
            .size(Setting.CellSize, Setting.CellSize)
            .clip(RoundedCornerShape(2.dp))
            .background(color)
    }
    Box(
        modifier
    ){
        if(string!=null)
        {
            Text(text = string,modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun EmptyCell(){
    Box(
        modifier = Modifier
            .size(Setting.CellSize,Setting.CellSize)
            .clip(RoundedCornerShape(2.dp))
    )
}

@Composable
fun TextCell(string: String){
    Box(
        modifier = Modifier
            .size(Setting.CellSize, Setting.CellSize)
            .clip(RoundedCornerShape(2.dp)),
    ){
        Text(text = string,
            modifier = Modifier.align(Alignment.Center)
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = Setting.MonthSize)
    }
}