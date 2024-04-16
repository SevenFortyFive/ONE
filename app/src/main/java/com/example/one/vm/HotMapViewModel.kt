package com.example.one.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.one.data.hotmapdata.HotMapClass
import com.example.one.data.hotmapdata.HotMapState
import com.example.one.data.hotmapdata.db.MyHotMapDatabase
import com.example.one.data.hotmapdata.entity.MyHotMapData
import com.example.one.data.hotmapdata.repository.MyHotMapDataRepository
import com.example.one.helper.MyData
import kotlinx.coroutines.launch

class HotMapVM(val app: Application): AndroidViewModel(app){

    //实例化Database对象
    private val db: MyHotMapDatabase by lazy {
        Room.databaseBuilder(
            app, MyHotMapDatabase::class.java,
            "myhotmapdata.db"
        ).build()
    }

    //初始化Repository
    private var myHotMapDataRepository: MyHotMapDataRepository

    // 指定当前热图类型
    private var _currentclass = MutableLiveData(HotMapClass.BREATH)
    val currentclass :LiveData<HotMapClass>
        get() = _currentclass

    //供compose观察的UI状态
    private val _currentState: MutableLiveData<HotMapState> = MutableLiveData()
    val currentState: LiveData<HotMapState>
        get() = _currentState

    // 存储当前热图数据列表
    private var _data_1 = MutableLiveData<List<MyHotMapData>>()
    val data_1 : LiveData<List<MyHotMapData>>
        get() = _data_1
    private var _data_2 = MutableLiveData<List<MyHotMapData>>()
    val data_2 : LiveData<List<MyHotMapData>>
        get() = _data_2
    private var _data_3 = MutableLiveData<List<MyHotMapData>>()
    val data_3 : LiveData<List<MyHotMapData>>
        get() = _data_3
    private var _data_4 = MutableLiveData<List<MyHotMapData>>()
    val data_4 : LiveData<List<MyHotMapData>>
        get() = _data_4

    init {
        myHotMapDataRepository = MyHotMapDataRepository(db)
    }

    // 提取近三个月的数据
    fun getAllData_ThreeM(){
        Log.d("hotmap","协程启动")
        Log.d("hotmap","协程结束")
    }
}