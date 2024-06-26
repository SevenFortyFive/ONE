package com.example.one.helper

import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.YearMonth

/**
 * 获取当前时间描述
 */
fun getTimeScr():String{
    val hour = LocalTime.now().hour

    return if(hour < 10)
        "早上"
    else if(hour < 12)
        "上午"
    else if(hour < 18)
        "下午"
    else
        "傍晚"
}

val CurrentIntYear:Int = getCurrentYear()
val CurrentIntMonth:Int = getCurrentMonth()
val CurrentIntDay:Int = getCurrentDay()

/**
 * 获取当前是当前月份的哪一天
 */
fun getCurrentDay():Int{
    return LocalDate.now().dayOfMonth
}

/**
 * 返回当前是某年某月
 */
fun getCurrentMonthAndYear(): Pair<Int,Int>{
    val currentData = LocalDate.now()
    return Pair(currentData.year,currentData.monthValue)
}

/**
 * 返回当前年份
 */
fun getCurrentYear():Int{
    return LocalDate.now().year
}

/**
 * 返回当前月份
 */
fun getCurrentMonth():Int{
    return LocalDate.now().monthValue
}

/**
 * 返回当前月份一日是星期几
 */
fun getCurrentMonthBeginWithWhichInMonth(year: Int = CurrentIntYear,month: Int = CurrentIntMonth): Int {
    return LocalDate.of(year, month,1).dayOfWeek.value
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
    val yearMonth = YearMonth.of(year,month)
    return yearMonth.lengthOfMonth()
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