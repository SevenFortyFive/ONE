package com.example.one.myui

import android.app.Application
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one.data.hotmapdata.entity.MyHotMapData
import com.example.one.helper.getCaraWithLe
import com.example.one.helper.getCurrentDay
import com.example.one.helper.getCurrentMonth
import com.example.one.helper.getNumOfDay
import com.example.one.setting.HotMapSetting
import com.example.one.ui.theme.ONETheme
import com.example.one.vm.HotMapVM
import com.example.one.vm.MyHotMapViewModelFactory


@Composable
fun HotMap()
{
    val vm:HotMapVM = viewModel(factory = MyHotMapViewModelFactory(LocalContext.current.applicationContext as Application))
    val HotMapState by vm.HotMapState.observeAsState()
    val data by vm.data.observeAsState()
    vm.initdata()
    vm.getAll()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .animateContentSize()
            .padding(10.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
                ) {
                Button(onClick = { vm.update() }) {
                    Text(text = "update")
                }
                for (i in 0..3) {
                    val numOfDay =
                        getNumOfDay(getCurrentMonth().first, getCurrentMonth().second - 3 + i)

                    val numOfColumn = (numOfDay / 7) + 1

                    // 标记某月的第几天
                    var currentDay = 1

                    Row {
                        for (k in 1..numOfColumn) {
                            Column {
                                if (k == 1)
                                    TextCell(string = getCaraWithLe(getCurrentMonth().second - 3 + i))
                                else
                                    EmptyCell()
                                Spacer(modifier = Modifier.height(HotMapSetting.CellPadding))
                                for (j in 1..7) {
                                    Log.d("idx",i.toString()+" "+currentDay.toString())
                                    Cell(
                                        (data?.get(i)?.get(currentDay)?.breath ?: 0),
                                        i == 3 && currentDay == getCurrentDay()
                                    )
                                    if (currentDay++ == numOfDay)
                                        break;
                                    if (j != 7)
                                        Spacer(modifier = Modifier.height(HotMapSetting.CellPadding))
                                }
                            }
                            if (k != numOfColumn || i != 3)
                                Spacer(modifier = Modifier.width(HotMapSetting.CellPadding))
                        }
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