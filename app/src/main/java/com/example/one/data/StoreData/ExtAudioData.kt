package com.example.one.data.StoreData

import com.example.one.R

data class ExtAudioData(
    val id:Long,
    val uri:String,
    val name: String,
    val surface: Int,
    val key:String,
    val cost:Int
)
/**
 * 返回可消费的audio列表
 */
fun getExtAudioList():List<ExtAudioData>{
    return listOf(
        ExtAudioData(10,"asset:///campfire.mp3","campfire", R.raw.img2,"AUDIO_1",100),
        ExtAudioData(11,"asset:///fanintheroom.mp3","fanintheroom", R.raw.img8,"AUDIO_2",100),
        ExtAudioData(12,"asset:///forest.mp3","forest", R.raw.img8,"AUDIO_3",100),
        ExtAudioData(13,"asset:///underwater.mp3","underwater", R.raw.img3,"AUDIO_4",100),
        ExtAudioData(14,"asset:///wandering.mp3","wandering", R.raw.img8,"AUDIO_5",100)
    )
}