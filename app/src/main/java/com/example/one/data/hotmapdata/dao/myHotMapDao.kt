package com.example.one.data.hotmapdata.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.one.data.hotmapdata.entity.MyHotMapData

@Dao
interface MyHotMapDao{
    @Insert
    suspend fun add(myHotMapData: MyHotMapData):Long

    @Delete
    suspend fun delete(myHotMapData: MyHotMapData)

    @Update
    suspend fun update(myHotMapData: MyHotMapData)

    @Query("select * from myhotmapdata")
    suspend fun getAll(): List<MyHotMapData>

    @Query("select * from myhotmapdata where id=:id")
    fun findById(id:Int):MyHotMapData

    @Query("select * from myhotmapdata where year=:year & month =:month")
    suspend fun findByData(year:Int,month:Int): List<MyHotMapData>
}