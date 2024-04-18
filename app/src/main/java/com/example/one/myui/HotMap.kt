package com.example.one.myui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.helper.MyData
import com.example.one.helper.getCaraWithLe
import com.example.one.setting.HotMapSetting
import com.example.one.ui.theme.ONETheme
import com.example.one.vm.HotMapVM
import kotlin.random.Random

@Composable
fun HotMap()
{
    val vm:HotMapVM = viewModel()
    vm.getAllData_ThreeM()

    Row{
        for(i in 0..3)
        {
            val numOfDay = MyData.getNumOfDay(MyData.currentYear,MyData.currentMonth-3+i)

            val numOfColumn = (numOfDay / 7) + 1

            // 标记某月的第几天
            var currentDay = 1

            Row{
                for(k in 1..numOfColumn)
                {
                    Column {
                        if(k==1)
                            TextCell(string = getCaraWithLe(MyData.currentMonth-3+i))
                        else
                            EmptyCell()
                        Spacer(modifier = Modifier.height(HotMapSetting.CellPadding))
                        for(j in 1..7)
                        {
                            Cell(value = Random.nextInt(10), i==3 && currentDay == MyData.currentDay)
                            if(currentDay++ == numOfDay)
                                break;
                            if(j != 7)
                                Spacer(modifier = Modifier.height(HotMapSetting.CellPadding))
                        }
                    }
                    if(k != numOfColumn || i != 3)
                        Spacer(modifier = Modifier.width(HotMapSetting.CellPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestHotMap()
{
    ONETheme {
        HotMap()
    }
}