package com.example.one.data.PlayerData

import android.net.Uri
import com.example.one.R
import com.example.one.data.SQLite.entity.MyAudioData

/**
 * 返回初始化的audio列表
 */
fun getAudioList(): List<MyAudioData> {
    return listOf(
        MyAudioData(0,"Bird","无名",R.raw.img1, null.toString(),
            Uri.parse("asset:///blackbird.mp3").toString(),
            false
        ),
        MyAudioData(1,"CityTraffic","无名",R.raw.img2, null.toString(),
            Uri.parse("asset:///citytraffic.mp3").toString(),
            false
        ),
        MyAudioData(2,"Rainy","无名",R.raw.img1, null.toString(),
            Uri.parse("asset:///rainy.mp3").toString(),
            false
        ),
        MyAudioData(3,"Wind","无名",R.raw.img3, null.toString(),
            Uri.parse("asset:///wind.mp3").toString(),
            false
        )
    )
}