package com.example.one.myui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one.ui.theme.ONETheme

@Composable
fun DrawerHotMap(modifier:Modifier){
//    val vm:HotMapViewModel = viewModel(LocalContext.current as ComponentActivity)
    Card(
        modifier = modifier
        .fillMaxWidth()
        .animateContentSize()
        .padding(10.dp)
            .height(100.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun TestDrawerHotMap()
{
    ONETheme {
        DrawerHotMap(Modifier)
    }
}