package com.example.one.vm

import com.example.one.helper.PixelAnalyzer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharRiverViewmodel() :ViewModel() {
    // 保存供外界观察的数据对象
    private val _data:MutableLiveData<Array<Array<Int>>> = MutableLiveData<Array<Array<Int>>>()

    val data:LiveData<Array<Array<Int>>>
        get() = _data

    private val _state:MutableLiveData<Boolean> = MutableLiveData(false)

    // 当前滚动状态，供外界观察
    val state:LiveData<Boolean>
        get() = _state

    // 保存滚动工作
    private var job: Job? = null

    private val _precision:MutableLiveData<Int> = MutableLiveData(30)

    val precision:LiveData<Int>
        get() = _precision

    /**
     * 讲数据进行滚动操作
     */
    private fun update(){
        // 假设 d 是一个可空的二维整数数组列表
        val firstColumn = _data.value?.map { it.first() }
        val remainingColumns = _data.value?.map { it.drop(1) }

        // 确保所有的列都非空，如果不是，则返回 null
        if (firstColumn != null && remainingColumns != null) {
            // 构建新数组，将剩余列和第一列连接起来
            val newArray = remainingColumns.mapIndexed { index, column ->
                (column.toIntArray() + firstColumn[index]).toTypedArray()
            }.toTypedArray()

            // 更新 _data.value
            _data.value = newArray
        }
    }

    /**
     * 获取job进行滚动
     */
    private fun getJob(): Job {
        val job =
            viewModelScope.launch {
                while (true)
                {
                    delay(100)
                    update()
                }
            }
        return  job
    }

    fun start(){
        if(_state.value == false)
        {
            this.job = getJob()
            this.job!!.start()
        }
        _state.value = true
    }

    fun stop(){
        if(_state.value == true)
        {
            this.job!!.cancel()
        }
        _state.value = false
    }

    fun setData(string:String,precision:Int){
        _precision.value = precision
        CoroutineScope(Dispatchers.IO).run {
            _data.value = PixelAnalyzer.analyzeCharacterPixels(string,precision)
        }
    }
}