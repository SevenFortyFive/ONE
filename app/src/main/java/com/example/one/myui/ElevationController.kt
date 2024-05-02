package com.example.one.myui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.one.setting.Setting

@Composable
fun ElevationController() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it
            Setting.WholeElevationLiveData.value = (it*100).toInt().dp
            }
            ,modifier = Modifier.padding(10.dp)
        )
        Text(text = (sliderPosition*10).toInt().toString())
    }
}