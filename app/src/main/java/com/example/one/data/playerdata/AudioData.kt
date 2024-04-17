package com.example.one.data.playerdata

import com.example.one.R

data class AudioData(
    val rawid: String,
    val name: String,
    val surface: Int
)

/**
 * 返回audio列表
 */
fun getAudioList(): List<AudioData> {
    return listOf(
        AudioData("res/raw/blackbird.mp3","",R.raw.img))
//        AudioData(R.raw.citytraffic,"citytraffic",R.raw.img),
//        AudioData(R.raw.rainy,"rainy",R.raw.img),
//        AudioData(R.raw.wind,"wind",R.raw.img))
}
