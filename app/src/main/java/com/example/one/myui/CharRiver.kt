package com.example.one.myui

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.vm.CharRiverViewmodel


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CharRiver(){
    val vm: CharRiverViewmodel = viewModel(LocalContext.current as ComponentActivity)

    val data by vm.data.observeAsState()

    val state = vm.state.observeAsState()

    Box(modifier = Modifier.fillMaxSize())
    {
        Column {
            if(data != null)
            {
                Column {
                    for(row in data?.indices!!)
                    {
                        Row {
                            for( col in data!![row].indices)
                            {
                                CellInCharRiver(value = data!![row][col])
                            }
                        }
                    }
                }
            }
            Button(onClick = {
                vm.changeState()
            }, modifier = Modifier.size(10.dp)) {
                Text(text = "Start")
            }
        }

    }

}

@Composable
fun CellInCharRiver(value: Int)
{
    val color = if(value != 0)
    {
        Color.Green
    }
    else{
        Gray
    }
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(4.dp, 4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(color)
    )
}