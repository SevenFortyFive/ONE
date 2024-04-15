package com.example.one.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.one.data.HotMapClass

class HotMapVM : ViewModel(){
    private var _currentclass = MutableLiveData(HotMapClass.BREATH)

    val currentclass :LiveData<HotMapClass>
        get() = _currentclass


}