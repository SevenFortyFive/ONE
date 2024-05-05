package com.example.one.myui

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.one.data.PlayerData.getAudioList
import com.example.one.data.SQLite.entity.MyAudioData
import com.example.one.vm.MyAudioViewModelFactory
import com.example.one.vm.PlayerViewModel

@Preview(showBackground = true)
@Composable
fun AudioList(){
    val vm: PlayerViewModel = viewModel(LocalContext.current as ComponentActivity
        ,factory = MyAudioViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    val dataList by vm.dataList.observeAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        AudioListHeader(Modifier.weight(2f))
        AudioListBody(dataList!!, modifier = Modifier.weight(4f))
        AudioControllerInAudioLIst(modifier = Modifier.weight(2f))
    }
}

@Composable
fun AudioListHeader(modifier: Modifier){
    Column(modifier = Modifier
        .fillMaxWidth()
        .then(modifier)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            MyAudioListItem(modifier = Modifier.size(100.dp))
            Row(modifier = Modifier.padding(15.dp)) {
                Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = "love")
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "待播清单")
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "正在播放音乐清单中的音乐")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "随机")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "循环")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "全部")
                }
            }
        }
    }

}

@Composable
fun AudioListBody(data:List<MyAudioData> = getAudioList(),modifier: Modifier){
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .then(modifier)) {
        items(data)
        {
            MyAudioListItem(data = it, modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun MyAudioListItem(data:MyAudioData = getAudioList().get(0),modifier: Modifier){
    Row(verticalAlignment = Alignment.CenterVertically) {
        AudioSurfaceInAudioList(imgID = data.surfaceId,
            modifier = modifier)
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = "name")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Author")
        }
    }
}

@Composable
fun AudioControllerInAudioLIst(modifier: Modifier){
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .then(modifier)){
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it }
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = sliderPosition.toString())
                Text(text = "100")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "big fore",
                    modifier = Modifier.size(100.dp))
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.PlayArrow, contentDescription = "big play",
                    modifier = Modifier.size(100.dp))
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "big next",
                    modifier = Modifier.size(100.dp))
            }
        }
    }

}

/**
 * 播放器列表中的所有封面
 */
@Composable
fun AudioSurfaceInAudioList(imgID:Int,modifier: Modifier = Modifier){
    Box(modifier = Modifier.then(modifier)){
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(imgID).apply(block = fun ImageRequest.Builder.() {
                    transformations()
                }).build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .matchParentSize()
                .aspectRatio(1.0f)
                .clip(RoundedCornerShape(8.dp))
                .then(modifier)
        )
    }
}