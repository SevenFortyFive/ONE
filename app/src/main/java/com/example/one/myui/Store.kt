package com.example.one.myui

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.one.R
import com.example.one.data.PlayerData.AudioData
import com.example.one.data.PlayerData.getExtAudioList
import com.example.one.ui.theme.ONETheme

@Composable
fun Store(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(350.dp)
        .animateContentSize()
        .padding(10.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)) {
        LazyColumn {
            items(getExtAudioList())
            {
                Item(it)
            }
        }
    }
}

@Composable
fun Item(data: AudioData){
    Card( modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .animateContentSize()
        .padding(10.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically) {
            CDInStore(data.surface,Modifier)
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.width(120.dp)) {
                Text(text = data.name)
                Spacer(modifier = Modifier.padding(1.dp))
                Text(text = data.uri)
            }
            Box(contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.fillMaxWidth())
            {

                Button(
                    onClick = {},
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(text ="购买")
                }
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun CDInStore(imgID: Int, modifier: Modifier) {
    Box(
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        //歌曲封面
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(imgID).apply(block = fun ImageRequest.Builder.() {
                    transformations(CircleCropTransformation())
                }).build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .matchParentSize()
                .aspectRatio(1.0f)
                .padding(20.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.bet),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .matchParentSize()
                .aspectRatio(1.0f)
                .padding(10.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Test(){
    ONETheme {
        Store()
    }
}