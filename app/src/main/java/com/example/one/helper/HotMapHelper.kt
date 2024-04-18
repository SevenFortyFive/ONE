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
