package com.example.one.helper

import com.example.one.data.hotmapdata.entity.MyHotMapData

var idx = 0L

fun getNewData():MyHotMapData{
    return MyHotMapData(idx++,1,1,1,1,1,1)
}