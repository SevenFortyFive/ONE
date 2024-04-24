package com.example.one.helper

import com.example.one.data.SQLite.entity.MyHotMapData

var idx = 0L

fun getNewData(): MyHotMapData {
    return MyHotMapData(idx++,1,1,1,1,1,1)
}