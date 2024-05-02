package com.example.one.myui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.one.data.MainPageState
import com.example.one.helper.CurrentIntDay
import com.example.one.helper.CurrentIntMonth
import com.example.one.helper.CurrentIntYear
import com.example.one.helper.getCaraWithLe
import com.example.one.helper.getCurrentMonthBeginWithWhichInMonth
import com.example.one.helper.getNumOfDay
import com.example.one.setting.Setting
import com.example.one.vm.HotMapViewModel


@Composable
fun HotMap(vm: HotMapViewModel, mainPageState: State<MainPageState?>, modifier:Modifier = Modifier)
{
    val data by vm.data.observeAsState()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(10.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(Setting.WholeElevation)) {
            Row(modifier = Modifier
//                .fillMaxSize()
                .padding(5.dp)
                .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
                ) {
                for (i in 0..3) {
                    val numOfDay =
                        getNumOfDay(CurrentIntYear, CurrentIntMonth - 3 + i)

                    val numOfColumn = (numOfDay / 7) + 1

                    // 标记某月的第几天
                    var currentDay = 1

                    Row {
                        for (k in 1..numOfColumn) {
                            Column {
                                if (k == 1)
                                    TextCell(string = getCaraWithLe(CurrentIntMonth - 3 + i))
                                else
                                    EmptyCell()
                                Spacer(modifier = Modifier.height(Setting.CellPadding))
                                for (j in 1..7) {
                                    val template = when(mainPageState.value)
                                    {
                                        MainPageState.BREATH -> data?.get(i)?.get(currentDay)?.breath ?: 0
                                        MainPageState.DRINK -> data?.get(i)?.get(currentDay)?.drink ?: 0
                                        MainPageState.CLOCK -> data?.get(i)?.get(currentDay)?.clock ?: 0
                                        MainPageState.MEDITATION -> data?.get(i)?.get(currentDay)?.breath ?: 0
                                        null -> -1
                                    }
                                    Cell(
                                        template,
                                        i == 3 && currentDay == CurrentIntDay
                                    )
                                    if (currentDay++ == numOfDay)
                                        break;
                                    if (j != 7)
                                        Spacer(modifier = Modifier.height(Setting.CellPadding))
                                }
                            }
                            if (k != numOfColumn || i != 3)
                                Spacer(modifier = Modifier.width(Setting.CellPadding))
                        }
                    }
                }
            }
        }
}

@Composable
fun ComprehensiveHotMap(vm: HotMapViewModel,modifier: Modifier = Modifier){
    val data by vm.data.observeAsState()

    Card(
        modifier = modifier
            .fillMaxWidth()
//            .height(200.dp)
            .animateContentSize()
            .padding(10.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(Setting.WholeElevation)
    ) {
        Column(modifier = Modifier.fillMaxSize()
            .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row{
                ComprehensiveTextCell(string = "日")
                Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                ComprehensiveTextCell(string = "一")
                Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                ComprehensiveTextCell(string = "二")
                Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                ComprehensiveTextCell(string = "三")
                Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                ComprehensiveTextCell(string = "四")
                Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                ComprehensiveTextCell(string = "五")
                Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                ComprehensiveTextCell(string = "六")
            }
            Spacer(modifier = Modifier.height(Setting.ComprehensiveHotMapCellPadding))
            // 获取当前月份天数
            val numOfDay = getNumOfDay(CurrentIntYear, CurrentIntMonth)
            // 当前天数
            var currentDay = 1
            // 计算有几行
            val numOfRow = numOfDay/7 + 1
            // 计算当前月份第一天是星期几
            val numOfEmpty = when(getCurrentMonthBeginWithWhichInMonth()){
                7 -> 1
                else -> getCurrentMonthBeginWithWhichInMonth()+1
            }
            Column {
                for(i in 1..numOfRow)
                {
                    // 第一行
                    if(i == 1)
                    {
                        Row {
                            for(j in 1..numOfEmpty)
                            {
                                ComprehensiveEmptyCell()
                                Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                            }
                            for(j in 1..7-numOfEmpty)
                            {
                                ComprehensiveHotMapCell(value =
                                        (data?.get(3)?.get(currentDay)?.breath ?: 0) +
                                        (data?.get(3)?.get(currentDay)?.clock ?: 0) +
                                        (data?.get(3)?.get(currentDay)?.drink ?: 0) +
                                        (data?.get(3)?.get(currentDay)?.clock ?: 0),
                                    currentDay == CurrentIntDay
                                )
                                if(j != 7 -numOfEmpty)
                                {
                                    Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                                }
                                currentDay++
                            }
                        }
                    }else{
                        Row {
                            for(j in 1..7)
                            {
                                ComprehensiveHotMapCell(value =
                                        (data?.get(3)?.get(currentDay)?.breath ?: 0) +
                                        (data?.get(3)?.get(currentDay)?.clock ?: 0) +
                                        (data?.get(3)?.get(currentDay)?.drink ?: 0) +
                                        (data?.get(3)?.get(currentDay)?.clock ?: 0),
                                    currentDay == CurrentIntDay
                                )
                                if(j != 7)
                                {
                                    Spacer(modifier = Modifier.width(Setting.ComprehensiveHotMapCellPadding))
                                }
                                currentDay++
                                if(currentDay == numOfDay)
                                {
                                    break
                                }
                            }
                        }
                    }
                    if(i != numOfRow)
                    {
                        Spacer(modifier = Modifier.height(Setting.ComprehensiveHotMapCellPadding))
                    }
                }
            }
        }
    }
}