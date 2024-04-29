package com.example.one.setting

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.one.helper.LocalDpHelper

object Setting{
    // HotMap中cell padding
    val CellPadding = 3.dp
    // HotMap中cell大小
    val CellSize = 15.dp
    // HotMap中月份字体大小
    val MonthSize = 10.sp

    // 主页中HotMap的大小
    val MainPageHotMapSize = LocalDpHelper.getUiDpHeight(0.2F)

    // 主页中Timer的大小
    val MainPageTimerSize = LocalDpHelper.getUiDpHeight(0.6F)

    // 主页中Controller的大小
    val MainPageControllerSize = LocalDpHelper.getUiDpHeight(0.15F)

}
