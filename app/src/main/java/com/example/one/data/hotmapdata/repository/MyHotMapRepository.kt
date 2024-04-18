package com.example.one.data.hotmapdata.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.one.data.hotmapdata.db.MyHotMapDatabase
import com.example.one.data.hotmapdata.entity.MyHotMapData

class MyHotMapDataRepository(private val db:MyHotMapDatabase) {
    suspend fun add(myData: MyHotMapData):Long{
        Log.d("database","is adding")
        return db.myHotMapDataDao().add(myData)
    }
    suspend fun getAllMyData():List<MyHotMapData>{
        return db.myHotMapDataDao().getAll()
    }
    suspend fun modify(myData: MyHotMapData){
        return db.myHotMapDataDao().update(myData)
    }

    suspend fun delete(myData: MyHotMapData){
        return db.myHotMapDataDao().delete(myData)
    }

    suspend fun findByData(year:Int, month: Int): List<MyHotMapData> {
        return db.myHotMapDataDao().findByData(year, month)
    }
}