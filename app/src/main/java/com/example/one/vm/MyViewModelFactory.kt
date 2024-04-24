package com.example.one.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//用于创建ViewModel的工厂方法
class MyHotMapViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HotMapViewModel::class.java)){
            return HotMapViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}