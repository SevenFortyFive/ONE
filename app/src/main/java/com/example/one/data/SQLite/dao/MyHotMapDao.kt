package com.example.one.data.SQLite.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.one.data.SQLite.entity.MyHotMapData

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

    @Query("select * from myhotmapdata where year=:year and month =:month and day=:day")
    suspend fun findByDataWithDay(year: Int, month: Int,day:Int): MyHotMapData?

    @Query("select * from myhotmapdata where id =:id")
    suspend fun findById(id:Long): MyHotMapData?
}