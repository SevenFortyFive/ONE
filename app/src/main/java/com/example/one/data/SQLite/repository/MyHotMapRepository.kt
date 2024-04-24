package com.example.one.data.SQLite.repository

import com.example.one.data.SQLite.db.MyHotMapDatabase
import com.example.one.data.SQLite.entity.MyHotMapData

class MyHotMapDataRepository(private val db: MyHotMapDatabase) {
    suspend fun add(myData: MyHotMapData):Long{
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

    suspend fun findByDataWithDay(year: Int,month: Int,day:Int): MyHotMapData?{
        return db.myHotMapDataDao().findByDataWithDay(year, month, day)
    }
    suspend fun findById(id:Long): MyHotMapData?{
        return db.myHotMapDataDao().findById(id)
    }
}