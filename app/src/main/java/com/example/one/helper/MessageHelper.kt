package com.example.one.helper

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


object MessageHelper {
    init {
        Log.d("init","init MessageHelper")
    }

    fun sendPareText(context: Context,data:String){
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT,data)
        shareIntent.type = "text/plain"
        try {
            context.startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, "没有合适的处理程序", Toast.LENGTH_SHORT)
                .show()
        }
    }
}