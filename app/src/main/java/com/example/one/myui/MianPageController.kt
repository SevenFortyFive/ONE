package com.example.one.myui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.one.data.MainPageState
import com.example.one.setting.Setting

@Composable
fun MainPageController(
    changeType: (MainPageState) -> Unit,
    modifier: Modifier
){
    Card(modifier = modifier
        .fillMaxWidth()
        .animateContentSize()
        .padding(10.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(Setting.WholeElevation)
    ) {
        Row(modifier = Modifier.fillMaxSize()
            .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {
            ElevatedButton(onClick = { changeType(MainPageState.BREATH)}) {
                Text(text = "Breath")
            }
            Spacer(modifier = Modifier.width(10.dp))
            ElevatedButton(onClick = {changeType(MainPageState.MEDITATION)}) {
                Text(text = "Modifier")
            }
            Spacer(modifier = Modifier.width(10.dp))
            ElevatedButton(onClick = {changeType(MainPageState.CLOCK)}) {
                Text(text = "Clock")
            }
            Spacer(modifier = Modifier.width(10.dp))
            ElevatedButton(onClick = {changeType(MainPageState.DRINK)}) {
                Text(text = "Drink")
            }
        }
    }
}