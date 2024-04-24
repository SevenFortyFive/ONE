package com.example.one.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.one.data.SharedPreferences.BREATH_TIME_KEY
import com.example.one.data.SharedPreferences.CLOCK_TIME_KEY
import com.example.one.data.SharedPreferences.DRINK_TIME_KEY
import com.example.one.data.SharedPreferences.MySharedPerference
import kotlinx.coroutines.launch

class SPViewModel:ViewModel() {
    private var _info: MutableLiveData<String> = MutableLiveData("")
    val info: LiveData<String>
        get() = _info

    val _breath_time:MutableLiveData<Int> = MutableLiveData(-1)
    val breath_time:LiveData<Int>
        get() = _breath_time

    val _clock_time:MutableLiveData<Int> = MutableLiveData(-1)
    val clock_time:LiveData<Int>
        get() = _clock_time

    val _drink_time:MutableLiveData<Int> = MutableLiveData(-1)
    val drink_time:LiveData<Int>
        get() = _drink_time

    init {
        viewModelScope.launch {
            _breath_time.value = MySharedPerference.getInt(BREATH_TIME_KEY)
            _clock_time.value = MySharedPerference.getInt(CLOCK_TIME_KEY)
            _drink_time.value = MySharedPerference.getInt(DRINK_TIME_KEY)
        }
    }
}