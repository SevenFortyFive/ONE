package com.example.one.helper

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object LocalDpHelper {
    private var dpHeight: Float? = null
    private var dpWidth: Float? = null

    fun init(context: Context){
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        dpHeight = (displayMetrics.heightPixels / displayMetrics.density)
        dpWidth = (displayMetrics.widthPixels / displayMetrics.density)

        Log.d("LocalDp","输出屏幕信息")
        Log.d("LocalDp", dpWidth.toString())
        Log.d("LocalDp", dpHeight.toString())
    }

    fun getDpWidth(): Float {
        return if(dpWidth != null) {
            dpWidth!!
        } else{
            0F
        }
    }

    fun getDpHeight(): Float {
        return if(dpHeight != null) {
            dpHeight!!
        } else{
            0F
        }
    }

    fun getUiDpHeight(value:Float): Dp {
        return if (dpHeight != null) {
            (dpHeight!! *value).dp
        } else {
            0.dp
        }
    }

    fun getUiDpWidth(value:Float):Dp{
        return if (dpWidth != null) {
            (dpWidth!! *value).dp
        } else {
            0.dp
        }
    }
}