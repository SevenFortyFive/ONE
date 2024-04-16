package com.example.one.data.hotmapdata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * hotmap数据库中的数据类，其中id为XXMM
 * data为DD日
 */
@Entity(tableName = "myhotmapdata")
data class MyHotMapData(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        var year: Int,
        var month: Int,
        var day: Int,
        var breath: Int,
        var clock: Int,
)