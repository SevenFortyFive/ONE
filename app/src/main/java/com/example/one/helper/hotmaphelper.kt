package com.example.one.helper

import androidx.compose.ui.graphics.Color
import com.example.one.ui.theme.Gray
import com.example.one.ui.theme.Green1
import com.example.one.ui.theme.Green2
import com.example.one.ui.theme.Green3
import com.example.one.ui.theme.Green4
import java.time.DateTimeException
import java.time.LocalDate
import java.time.YearMonth

/**从cell得到的value得到cell的颜色
 */
fun getColorForValue(value: Int): Color {
    return when {
        value == 0 -> Gray
        value < 2 -> Green1
        value < 5 -> Green2
        value < 8 -> Green3
        else -> Green4
    }
}

object MyData{

    val currentDay = LocalDate.now().dayOfMonth

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