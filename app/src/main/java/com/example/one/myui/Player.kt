package com.example.one.myui

import PlayerViewModel
import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.one.R
import com.example.one.data.playerdata.AudioData
import com.example.one.data.playerdata.PlayerState

@Composable
fun Player(){

    val vm:PlayerViewModel = viewModel(LocalContext.current as ComponentActivity)

    val playerstate = vm.currentState.observeAsState()
    val currentdata = vm.currentAudioData.observeAsState()
    val prename = vm.preName.observeAsState()
    val nextname = vm.nextName.observeAsState()

    var ifshow by remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
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
                    CD(isPlaying = (playerstate.value == PlayerState.PLAYING),
                        imgID = (currentdata.value as AudioData).surface,
                        modifier = Modifier)

                    Spacer(modifier = Modifier.height(2.dp))

                    Row {
                        Text(text = "上一首:"+prename.value as String)
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = (currentdata.value as AudioData).name)
                        Text(text = "下一首:"+nextname.value as String)
                    }


                    Spacer(modifier = Modifier.height(2.dp))

                    Row {
                        Button(onClick = {vm.preAudio()}) {
                            Text(text = "上一首")
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                        Button(onClick = {
                            if(playerstate.value == PlayerState.STOP)
                            {
                                vm.start()
                            }else{
                                vm.pause()
                            }
                        }) {
                            Text(text = if(playerstate.value == PlayerState.PLAYING)"暂停" else "开始")
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                        Button(onClick = {vm.nextAudio()}) {
                            Text(text = "下一首")
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
private fun CD(isPlaying: Boolean, imgID: Int, modifier: Modifier) {
    Box(
        modifier = modifier.padding(horizontal = 36.dp)
    ) {
        //唱片旋转角度
        val rotation = infiniteRotation(isPlaying)
        //唱针旋转角度
        val stylusRotation by animateFloatAsState(targetValue = if (isPlaying) 0f else -30f,
            label = "CD_ROUND"
        )
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
                .graphicsLayer {
                    rotationZ = rotation.value
                }
        )
        //唱片边框
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
        //唱片针
        Image(
            painter = painterResource(id = R.drawable.bgm),
            contentDescription = null,
            modifier = Modifier
                .align(BiasAlignment(0.3f, -1f))
                .graphicsLayer {
                    rotationZ = stylusRotation
                    transformOrigin = TransformOrigin(0f, 0f)
                }
        )
    }
}

/**
 * 无限循环的旋转动画
 */
@Composable
private fun infiniteRotation(
    startRotate: Boolean,
    duration: Int = 15 * 1000
): Animatable<Float, AnimationVector1D> {
    var rotation by remember { mutableStateOf(Animatable(0f)) }
    LaunchedEffect(key1 = startRotate, block = {
        if (startRotate) {
            //从上次的暂停角度 -> 执行动画 -> 到目标角度（+360°）
            rotation.animateTo(
                (rotation.value % 360f) + 360f, animationSpec = infiniteRepeatable(
                    animation = tween(duration, easing = LinearEasing)
                )
            )
        } else {
            rotation.stop()
            //初始角度取余是为了防止每次暂停后目标角度无限增大
            rotation = Animatable(rotation.value % 360f)
        }
    })
    return rotation
}