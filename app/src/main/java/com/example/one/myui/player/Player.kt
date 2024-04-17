package com.example.one.myui.player

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.R
import com.example.one.ui.theme.ONETheme
import com.example.one.vm.PlayerViewModel

@Composable
fun Player(){
    var vm :PlayerViewModel= viewModel()

    vm.initdata(LocalContext.current)

    var ifshow by remember {
        mutableStateOf(false)
    }
    // 创建动画状态
    val sizeState by animateDpAsState(
        if (ifshow) 250.dp else 100.dp
    )
    Card(
        modifier = Modifier
            .absoluteOffset(100.dp, 300.dp)
            .size(sizeState)
            .animateContentSize()
            .padding(10.dp)
            .clickable { ifshow = !ifshow },
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            if(ifshow)
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImageSurface()
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "name")
                    Spacer(modifier = Modifier.height(2.dp))
                    Row {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "left")
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                        Button(onClick = {vm.play()}) {
                            Text(text = "begin")
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "right")
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun ImageSurface()
{
    Card(modifier = Modifier
        .animateContentSize()
        .size(150.dp)
        .padding(10.dp),
        shape = RoundedCornerShape(10.dp)) {
        Image(painter = painterResource(id = R.raw.img), contentDescription = "test")
    }
}
@Preview(showBackground = true)
@Composable
fun TestPlayer()
{
    ONETheme {
        Player()
    }
}