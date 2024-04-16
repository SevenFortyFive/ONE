package com.example.one.data.hotmapdata.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.one.data.hotmapdata.dao.MyHotMapDao
import com.example.one.data.hotmapdata.entity.MyHotMapData

@Database(
    entities = [MyHotMapData::class],  //表明本数据库中有几张表
    version = 1,  //当前数据库的版本号（重要！）
    exportSchema = false //不导出Schema
)
abstract class MyHotMapDatabase: RoomDatabase() {
    //引用存取特定表的数据存取对象
    abstract fun myHotMapDataDao():MyHotMapDao
}