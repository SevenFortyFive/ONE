package com.example.one.helper

import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth

fun getTimeScr():String{
    val hour = LocalTime.now().hour

    return if(hour < 10)
        "早上"
    else if(hour < 12)
        "上午"
    else if(hour < 18)
        "下午"
    else
        "晚上"
}


object MyData{

    val currentDay = LocalDate.now().dayOfMonth
    val currentYear = LocalDate.now().year
    val currentMonth = LocalDate.now().month.value

    /**
     * 返回当前是某年某月
     */
    fun getCurrentMonth(): Pair<Int,Int>{
        val currentData = LocalDate.now()
        return Pair(currentData.year,currentData.monthValue)
    }

    /**
     * 检查输入日期是否存在
     */
    fun isData(year:Int,month:Int,day:Int):Boolean
    {
        return try {
            LocalDate.of(year,month,day)
            true
        }catch(e: DateTimeException)
        {
            false
        }
    }

    /**
     * 获取当前年月共有几天
     */
    fun getNumOfDay(year:Int,month:Int):Int{
        val yearmonth = YearMonth.of(year,month)
        return yearmonth.lengthOfMonth()
    }
}

fun getCaraWithLe(letter: Int):String{
    return when(letter)
    {
        1 -> "一"
        2 -> "二"
        3 -> "三"
        4 -> "四"
        5 -> "五"
        6 -> "六"
        7 -> "七"
        8 -> "八"
        9 -> "九"
        10 -> "拾"
        11 -> "冬"
        13 -> "腊"
        else -> "?"
    }
}