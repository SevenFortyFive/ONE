package com.example.one.data.PlayerData

import com.example.one.R

data class AudioData(
    val uri:String,
    val name: String,
    val surface: Int
)

/**
 * 返回初始化的audio列表
 */
fun getAudioList(): List<AudioData> {
    return listOf(
        AudioData("asset:///blackbird.mp3","bird",R.raw.img),
        AudioData("asset:///citytraffic.mp3","citytraffic",R.raw.img1),
        AudioData("asset:///rainy.mp3","rainy",R.raw.img3),
        AudioData("asset:///wind.mp3","wind",R.raw.img8))
}