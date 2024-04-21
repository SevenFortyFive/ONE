package com.example.one.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.one.data.hotmapdata.HotMapState
import com.example.one.data.hotmapdata.db.MyHotMapDatabase
import com.example.one.data.hotmapdata.entity.MyHotMapData
import com.example.one.data.hotmapdata.repository.MyHotMapDataRepository
import com.example.one.helper.getCurrentMonth
import com.example.one.helper.getNewHotMapData
import com.example.one.helper.getNumOfDay
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    // 存储加载到内存中的热图数据
    private var _data:MutableLiveData<Array<Array<MyHotMapData>>> = MutableLiveData()
    val data :LiveData<Array<Array<MyHotMapData>>>
        get() = _data

    private var _HotMapState:MutableLiveData<HotMapState> = MutableLiveData(com.example.one.data.hotmapdata.HotMapState.LOADING)
    val HotMapState:LiveData<HotMapState>
        get() = _HotMapState

    //初始化Repository
    private var myHotMapDataRepository: MyHotMapDataRepository

    init {
        myHotMapDataRepository = MyHotMapDataRepository(db)
    }

    /**
     * 对数据进行初始化
     */
    fun initdata() {
        viewModelScope.launch {
            _HotMapState.value = com.example.one.data.hotmapdata.HotMapState.LOADING
            val (y, m) = getCurrentMonth()
            val newarray = Array(5) { Array(32) { getNewHotMapData() } }

            val deferreds = (0..3).map { i ->
                async {
                    val numberOfDay = getNumOfDay(y, m + i - 3)
                    for (j in 1..numberOfDay) {
                        val QData = myHotMapDataRepository.findByDataWithDay(y, m, j)
                        if (QData == null) {
                            myHotMapDataRepository.add(getNewHotMapData())
                        } else {
                            newarray[i][j] = QData
                        }
                    }
                }
            }

            // 等待所有协程执行完毕
            deferreds.awaitAll()

            // 所有协程执行完毕后，将新数组赋值给 _data
            _data.value = newarray

            // 数据加载完成，将状态设置为 READY
            _HotMapState.value = com.example.one.data.hotmapdata.HotMapState.READY
        }
    }
    fun update(){
        initdata()
    }
    fun getAll()
    {
        viewModelScope.launch {
            val list = myHotMapDataRepository.getAllMyData()
            Log.d("getall",list.toString())
        }
    }
}