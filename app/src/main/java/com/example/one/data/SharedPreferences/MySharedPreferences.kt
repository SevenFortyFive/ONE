package com.example.one.data.SharedPreferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object MySharedPreference{
    private lateinit var SharedPreferences: SharedPreferences

    private lateinit var editor: SharedPreferences.Editor

    private const val SAVEDDATA = "saved_data"
    fun initSharedPreferences(context: Context){
        SharedPreferences = context.getSharedPreferences(SAVEDDATA,MODE_PRIVATE)
        editor = SharedPreferences.edit()
    }


    fun getInt(key:String):Int
    {
        return SharedPreferences.getInt(key,0)
    }

    fun getBool(key: String):Boolean{
        return SharedPreferences.getBoolean(key,false)
    }

    fun editBooleanData(key:String,value:Boolean):Boolean
    {
        editor.putBoolean(key,value)
        return editor.commit()
    }

    fun editIntData(key:String, value:Int):Boolean
    {
        editor.putInt(key,value)
        return editor.commit()
    }
}