package com.example.one.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.one.data.SharedPreferences.BREATH_TIME_KEY
import com.example.one.data.SharedPreferences.CLOCK_TIME_KEY
import com.example.one.data.SharedPreferences.DRINK_TIME_KEY
import com.example.one.data.SharedPreferences.MySharedPreference
import kotlinx.coroutines.launch

class SPViewModel:ViewModel() {
    private var _info: MutableLiveData<String> = MutableLiveData("")
    val info: LiveData<String>
        get() = _info

    private val _breathTime:MutableLiveData<Int> = MutableLiveData(-1)
    val breathTime:LiveData<Int>
        get() = _breathTime

    private val _clockTime:MutableLiveData<Int> = MutableLiveData(-1)
    val clockTime:LiveData<Int>
        get() = _clockTime

    private val _drinkTime:MutableLiveData<Int> = MutableLiveData(-1)
    val drinkTime:LiveData<Int>
        get() = _drinkTime

    init {
        viewModelScope.launch {
            _breathTime.value = MySharedPreference.getInt(BREATH_TIME_KEY)
            _clockTime.value = MySharedPreference.getInt(CLOCK_TIME_KEY)
            _drinkTime.value = MySharedPreference.getInt(DRINK_TIME_KEY)
        }
    }
}