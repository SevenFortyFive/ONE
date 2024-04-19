package com.example.one.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.one.data.hotmapdata.db.MyHotMapDatabase
import com.example.one.data.hotmapdata.repository.MyHotMapDataRepository
import com.example.one.helper.getNewData
import kotlinx.coroutines.launch

class HotMapVM(val app: Application): AndroidViewModel(app){

    //实例化Database对象
    private val db: MyHotMapDatabase by lazy {
        Room.databaseBuilder(
            app, MyHotMapDatabase::class.java,
            "myhotmapdata.db"
        ).build()
    }

    private var _info:MutableLiveData<String> = MutableLiveData("没有数据")

    val info:LiveData<String>
        get() = _info

    //初始化Repository
    private var myHotMapDataRepository: MyHotMapDataRepository

    init {
        myHotMapDataRepository = MyHotMapDataRepository(db)
    }

    // 提取近三个月的数据
    fun getAllData(){
        viewModelScope.launch {
            var datalist = myHotMapDataRepository.getAllMyData()
            if(datalist.isNotEmpty())
            {
                _info.value = datalist.toString()
                Log.d("data",datalist.toString())
            }
            else{
                _info.value = "没有数据"
            }
        }
    }

    fun add() {
        viewModelScope.launch {
            myHotMapDataRepository.add(getNewData())
        }
    }
}