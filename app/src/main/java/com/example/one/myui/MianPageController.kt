package com.example.one.myui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one.page.MainPageState
import com.example.one.ui.theme.ONETheme

@Composable
fun MainPageController(mainPageState: MutableState<MainPageState>){
    Card(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .animateContentSize()
            .padding(10.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { mainPageState.value = MainPageState.BREATH}) {
                Text(text = "Breath")
            }
            Button(onClick = {mainPageState.value = MainPageState.MEDITATION}) {
                Text(text = "Modifier")
            }
            Button(onClick = {mainPageState.value = MainPageState.CLOCK}) {
                Text(text = "Clock")
            }
            Button(onClick = {mainPageState.value = MainPageState.DRINK}) {
                Text(text = "Drink")
            }
        }
    }
}