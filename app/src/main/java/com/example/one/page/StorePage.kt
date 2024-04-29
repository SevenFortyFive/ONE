package com.example.one.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.one.myui.StoreUi

@Composable
fun StorePage(){
    Box(modifier = Modifier.fillMaxSize())
    {
        StoreUi()
    }
}