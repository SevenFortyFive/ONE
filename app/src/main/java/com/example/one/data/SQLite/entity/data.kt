package com.example.one.data.SQLite.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * HotMap数据库中的数据类
 */
@Entity(tableName = "MyHotMapData")
data class MyHotMapData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var year: Int,
    var month: Int,
    var day: Int,
    var breath: Int,
    var clock: Int,
    var drink:Int
)

/**
 * Audio数据库中的数据类
 */
@Entity(tableName = "MyAudioData")
data class MyAudioData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name:String,
    val author:String,
    val surfaceId:Int,
    val surfaceUri:String,
    val uri:String,
    var love:Boolean
)