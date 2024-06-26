package com.example.one.vm

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.example.one.data.PlayerData.PlayerState
import com.example.one.data.PlayerData.getAudioList
import com.example.one.data.SQLite.db.MyAudioDatabase
import com.example.one.data.SQLite.entity.MyAudioData
import com.example.one.data.SQLite.repository.MyAudioRepository
import com.example.one.data.SharedPreferences.SharedPreferencesHelper
import com.example.one.data.StoreData.ExtAudioData
import com.example.one.data.StoreData.getExtAudioList
import com.example.one.player.ExoPlayerManager
import kotlinx.coroutines.launch


class PlayerViewModel(private val app: Application): AndroidViewModel(app) {
    private var player: ExoPlayer = ExoPlayerManager.getExoPlayer()!!

    // 保存列表
    private var _dataList:MutableLiveData<MutableList<MyAudioData>> = MutableLiveData()

    val dataList: LiveData<MutableList<MyAudioData>>
        get() = _dataList

    private var _currentAudioData: MutableLiveData<MyAudioData> = MutableLiveData()
    val currentAudioData: LiveData<MyAudioData>
        get() = _currentAudioData

    private var _currentState: MutableLiveData<PlayerState> = MutableLiveData(PlayerState.STOP)

    val currentState: LiveData<PlayerState>
        get() = _currentState

    // 实例化Database对象
    private val db: MyAudioDatabase by lazy {
        Room.databaseBuilder(
            app, MyAudioDatabase::class.java,
            "MyAudioData.db"
        ).build()
    }
    // 初始化Repository
    private var myAudioRepository:MyAudioRepository = MyAudioRepository(db)

    init {
        Log.d("com.example.one.vm.PlayerViewModel","Init")

        _currentState.postValue(PlayerState.LOADING)

        viewModelScope.launch {
            val temList : MutableList<MyAudioData> = mutableListOf()
            // 处理初始化的列表
            getAudioList().forEach {
                // 去数据库中查询
                val temData = myAudioRepository.findById(it.id)
                // 没有查到存入数据库
                if(temData == null)
                {
                    myAudioRepository.add(it)
                }
                temList.add(it)
                val mediaItem = MediaItem.fromUri(it.uri)
                player.addMediaItem(mediaItem)
            }

            // 处理额外的列表
            getExtAudioList().forEach{
                if(SharedPreferencesHelper.getIfAudioOk(it.key))
                {
                    val temData = myAudioRepository.findById(it.id)
                    val newAudioData = MyAudioData(it.id,it.name,"无名",it.surface,
                        null.toString(),Uri.parse(it.uri).toString(),false)
                    // 检查是否已经存入
                    if(temData == null)
                    {
                        myAudioRepository.add(newAudioData)
                    }
                    val mediaItem = MediaItem.fromUri(newAudioData.uri)
                    player.addMediaItem(mediaItem)
                    temList.add(newAudioData)
                }else{
                    Log.d("ExAudio",it.name+" unlocked")
                }
            }
            _currentAudioData.value = temList[0]
            _dataList.value = temList
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
        _currentAudioData.postValue(_dataList.value!!.get(player.currentMediaItemIndex))
    }

    /**
     * 从当前索引得到前一个索引
     */
    private fun getPreIdx(currentIdx: Int): Int {
        return if (currentIdx == 0)
            this._dataList.value?.size!! - 1
        else {
            player.currentMediaItemIndex - 1
        }
    }

    /**
     * 从当前索引得到下一个索引
     */
    private fun getNextIdx(currentIdx: Int): Int {
        return if (currentIdx == _dataList.value?.size!! - 1) {
            0
        } else {
            player.currentMediaItemIndex + 1
        }
    }

    /**
     * 外部调用，添加音乐曲目
     */
    fun unlockExAudioItem(audioData: ExtAudioData){
        viewModelScope.launch {
            val temData = MyAudioData(audioData.id,audioData.name,"无名",audioData.surface,
                null.toString(),Uri.parse(audioData.uri).toString(),false
            )

            if(myAudioRepository.findById(audioData.id) == null)
            {
                myAudioRepository.add(temData)
            }
            _dataList.value?.add(temData)
        }
    }

    fun changeItemLoveState(data:MyAudioData){
        viewModelScope.launch {
            val temData = myAudioRepository.findByNameAndAuthor(data.name,data.author)
            if(temData != null)
            {
                temData.love = !temData.love
                myAudioRepository.modify(temData)
            }
        }
    }
}