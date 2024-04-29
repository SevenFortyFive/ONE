package com.example.one.myui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.one.page.MainPageState

@Composable
fun MainPageController(mainPageState: MutableState<MainPageState>,modifier: Modifier){
    Card(modifier =
        modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(10.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {
            ElevatedButton(onClick = { mainPageState.value = MainPageState.BREATH}) {
                Text(text = "Breath")
            }
            ElevatedButton(onClick = {mainPageState.value = MainPageState.MEDITATION}) {
                Text(text = "Modifier")
            }
            ElevatedButton(onClick = {mainPageState.value = MainPageState.CLOCK}) {
                Text(text = "Clock")
            }
            ElevatedButton(onClick = {mainPageState.value = MainPageState.DRINK}) {
                Text(text = "Drink")
            }
        }
    }
}