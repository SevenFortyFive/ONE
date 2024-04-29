package com.example.one.myui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.helper.LocalDpHelper
import com.example.one.vm.CharRiverViewmodel


@Composable
fun CharRiver(){
    val vm: CharRiverViewmodel = viewModel(LocalContext.current as ComponentActivity)

    val data by vm.data.observeAsState()

    val state = vm.state.observeAsState()

    val precision by vm.precision.observeAsState()

    val ifShow = remember {
        mutableStateOf(true)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    ifShow.value = !ifShow.value
                }
            )
        },
      contentAlignment = Alignment.Center
    )
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
                                CellInCharRiver(value = data!![row][col],precision)
                            }
                        }
                    }
                }
            }
        }
        if(ifShow.value)
        {
            CharRiverEditor(Modifier,vm::start,vm::stop,vm::setData)
        }
    }
}

@Composable
fun CellInCharRiver(value: Int,pre:Int? = 30)
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
            .size((LocalDpHelper.getDpHeight().dp - (LocalDpHelper.getDpHeight() * 0.06).dp) / pre!! - 4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(color)
    )
}