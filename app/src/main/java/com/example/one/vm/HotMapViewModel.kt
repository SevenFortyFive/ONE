package com.example.one.vm

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.one.data.MainPageState
import com.example.one.data.SQLite.db.MyHotMapDatabase
import com.example.one.data.SQLite.entity.MyHotMapData
import com.example.one.data.SQLite.repository.MyHotMapDataRepository
import com.example.one.helper.CurrentIntDay
import com.example.one.helper.CurrentIntMonth
import com.example.one.helper.CurrentIntYear
import com.example.one.helper.MessageHelper
import com.example.one.helper.getCurrentDay
import com.example.one.helper.getCurrentMonthAndYear
import com.example.one.helper.getNewHotMapData
import com.example.one.helper.getNumOfDay
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class HotMapViewModel(private val app:Application):AndroidViewModel(app) {
    //实例化Database对象
    private val db: MyHotMapDatabase by lazy {
        Room.databaseBuilder(
            app, MyHotMapDatabase::class.java,
            "MyHotMapData.db"
        ).build()
    }

    // 保存主页的状态
    private var _Type:MutableLiveData<MainPageState> = MutableLiveData(MainPageState.BREATH)
    val Type:LiveData<MainPageState>
        get() = _Type

    //初始化Repository
    private var myHotMapDataRepository: MyHotMapDataRepository = MyHotMapDataRepository(db)

    // 保存数组副本
    private var dataCopied:Array<Array<MyHotMapData>> = Array(5){Array(32){ getNewHotMapData(0,0,0)} }

    // 存储加载到内存中的热图数据
    private var _data: MutableLiveData<Array<Array<MyHotMapData>>> = MutableLiveData()
    val data : LiveData<Array<Array<MyHotMapData>>>
        get() = _data

    init {
        Log.d("HotMapViewModel","init")
        viewModelScope.launch {
            val (y, m) = getCurrentMonthAndYear()
            val newArray = Array(5) { Array(32){ getNewHotMapData(0,0,0)} }

            val deferred = (0..3).map { i ->
                async {
                    val numberOfDay = getNumOfDay(y, m + i - 3)
                    for (j in 1..numberOfDay) {
                        val QData = myHotMapDataRepository.findByDataWithDay(y, m-3+i, j)
                        if (QData == null) {
                            val newData = getNewHotMapData(y,m-3+i,j)
                            myHotMapDataRepository.add(newData)
                            newArray[i][j] = newData
                        } else {
                            newArray[i][j] = QData
                        }
                    }
                }
            }

            // 等待所有协程执行完毕
            deferred.awaitAll()

            // 所有协程执行完毕后，将新数组赋值给 _data
            _data.value = newArray

            // 保存副本
            dataCopied = newArray
        }
    }

    fun update(flag:Int){
        viewModelScope.launch {
            val temData = myHotMapDataRepository.findByDataWithDay(
                CurrentIntYear,
                CurrentIntMonth,
                CurrentIntDay
            )
            Log.d("FindData",temData.toString())
            if (temData != null) {
                when (flag) {
                    1 -> temData.breath++
                    2 -> temData.clock++
                    3 -> temData.drink++
                }
                // 修改副本
                dataCopied[3][getCurrentDay()] = temData
                // post副本
                _data.value = dataCopied.clone()
                myHotMapDataRepository.modify(temData)
            }
        }
    }

    fun changeType(changeTo:MainPageState)
    {
        viewModelScope.launch {
            _Type.value = changeTo
        }
    }

    fun sendPareTextMessage(context: Context,data:String){
        viewModelScope.launch {
            MessageHelper.sendPareText(context,data)
        }
    }

    fun sharDetailHotMapMessage(context: Context){
        viewModelScope.launch {
            val builder = StringBuilder()
            val formattedString = "%-6s | %-3s| %-3s| %-5s | %-5s\n"
            builder.append(String.format(formattedString,"年","月","日","深呼吸","番茄钟"))
            myHotMapDataRepository.getAllMyData().forEach{
                builder.append(String.format(formattedString,
                    it.year.toString(),
                    it.month.toString(),
                    it.day.toString(),
                    it.breath.toString(),
                    it.clock.toString())
                )
            }
            sendPareTextMessage(context,builder.toString())
        }
    }
}