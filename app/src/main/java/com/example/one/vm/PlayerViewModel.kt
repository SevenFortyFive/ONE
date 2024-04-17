package com.example.one.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.one.data.playerdata.AudioData
import com.example.one.data.playerdata.PlayerState
import com.example.one.data.playerdata.getAudioList
import kotlinx.coroutines.launch

class PlayerViewModel(): ViewModel(){

    // 保存列表
    private val datalist: List<AudioData> = getAudioList()

    // 当前播放曲目索引
    private var _currentidx = MutableLiveData(0)

    private var _currentstate = MutableLiveData(PlayerState.STOP)

    // 标记当前状态
    val currentstate :LiveData<PlayerState>
        get() = _currentstate

    lateinit var player : ExoPlayer

    init {
        Log.d("player","init playerviewmodel")
    }
    fun initdata(context: Context){
        Log.d("player","initdata start")

        player = ExoPlayer.Builder(context)
            .setAudioAttributes(
                AudioAttributes.DEFAULT,/* handleAudioFocus= */true)
            .setHandleAudioBecomingNoisy(true)
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()

        viewModelScope.launch{
            datalist.forEach{
                player.addMediaItem(
                    MediaItem.fromUri(it.rawid)
                )
            }
        }
        Log.d("player","innitdata over")
    }

    fun play(){
        viewModelScope.launch {
            try {
                Log.d("player","try to start")
                player.prepare()
                player.play()
            }catch (e :Exception){
                Log.d("player","fail to start")
            }
        }
    }
}