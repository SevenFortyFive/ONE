package com.example.one.vm

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.one.data.PlayerData.AudioData
import com.example.one.data.PlayerData.PlayerState
import com.example.one.data.PlayerData.getAudioList
import com.example.one.data.SharedPreferences.SharedPreferencesHelper
import com.example.one.data.StoreData.ExtAudioData
import com.example.one.data.StoreData.getExtAudioList
import com.example.one.player.ExoPlayerManager
import kotlinx.coroutines.launch


class PlayerViewModel(): ViewModel() {
    private var player: ExoPlayer = ExoPlayerManager.getExoPlayer()!!

    private var dataList:MutableList<AudioData> = getAudioList().toMutableList()

    private var _currentAudioData: MutableLiveData<AudioData> = MutableLiveData(dataList[0])
    val currentAudioData: LiveData<AudioData>
        get() = _currentAudioData

    private var _currentState: MutableLiveData<PlayerState> = MutableLiveData(PlayerState.STOP)

    val currentState: LiveData<PlayerState>
        get() = _currentState

    // 上一首歌的名字
    private var _preName:MutableLiveData<String> = MutableLiveData(dataList[getPreIdx(player.currentMediaItemIndex)].name)
    val preName: LiveData<String>
        get() = _preName

    // 下一首歌的名字
    private var _nextName:MutableLiveData<String> = MutableLiveData(dataList[getNextIdx(player.currentMediaItemIndex)].name)
    val nextName: LiveData<String>
        get() = _nextName

    init {
        Log.d("com.example.one.vm.PlayerViewModel","Init")

        _currentState.postValue(PlayerState.LOADING)
        viewModelScope.launch {
            dataList.forEach {
                val mediaItem = MediaItem.fromUri(Uri.parse(it.uri))
                player.addMediaItem(mediaItem)
            }

            getExtAudioList().forEach{
                if(SharedPreferencesHelper.getIfAudioOk(it.key))
                {
                    val mediaItem = MediaItem.fromUri(Uri.parse((it.uri)))
                    player.addMediaItem(mediaItem)
                    dataList.add(AudioData(it.uri,it.name,it.surface))
                }
            }
        }
        player.repeatMode
        _currentState.postValue(PlayerState.STOP)
    }

    fun start() {
        viewModelScope.launch {
            Log.d("player", player.currentMediaItemIndex.toString())
            _currentState.postValue(PlayerState.PLAYING)
            player.prepare()
            player.play()
        }
    }

    fun pause() {
        _currentState.postValue(PlayerState.STOP)
        player.pause()
    }

    fun nextAudio() {
        this.player.seekToDefaultPosition(getNextIdx(player.currentMediaItemIndex))
        this.onDataChanged()
        this.start()
    }

    fun preAudio() {
        this.player.seekToDefaultPosition(getPreIdx(player.currentMediaItemIndex))
        this.onDataChanged()
        this.start()
    }

    private fun onDataChanged(){
        _preName.postValue(dataList[getPreIdx(player.currentMediaItemIndex)].name)
        _nextName.postValue(dataList[getNextIdx(player.currentMediaItemIndex)].name)
        _currentAudioData.postValue(dataList[player.currentMediaItemIndex])
    }

    /**
     * 从当前索引得到前一个索引
     */
    private fun getPreIdx(currentIdx: Int): Int {
        return if (currentIdx == 0)
            this.dataList.size - 1
        else {
            player.currentMediaItemIndex - 1
        }
    }

    /**
     * 从当前索引得到下一个索引
     */
    private fun getNextIdx(currentIdx: Int): Int {
        return if (currentIdx == dataList.size - 1) {
            0
        } else {
            player.currentMediaItemIndex + 1
        }
    }

    /**
     * 外部调用，添加音乐曲目
     */
    fun addItem(audioData: ExtAudioData){
        viewModelScope.launch {
            dataList.add(AudioData(audioData.uri,audioData.name,audioData.surface))
            val mediaItem = MediaItem.fromUri(Uri.parse((audioData.uri)))
            player.addMediaItem(mediaItem)
        }
    }
}