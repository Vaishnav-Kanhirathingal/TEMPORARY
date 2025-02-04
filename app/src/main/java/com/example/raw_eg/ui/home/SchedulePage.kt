package com.example.raw_eg.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.raw_eg.MainViewModel
import com.example.raw_eg.data.schedule.Schedule

object SchedulePage {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun MainScreen(
        modifier: Modifier,
        viewModel: MainViewModel
    ) {
        LazyColumn(
            modifier = modifier,
            content = {
                fun Schedule.getMonthYearCombo(): String {
                    return this.zonedDateTime.let { "${it.month.value} - ${it.year}" }
                }

                val partitionedScheduleList =
                    viewModel.scheduleList.groupBy { it.getMonthYearCombo() }

//                val partitioned = viewModel.scheduleList.partition {
//                    val newMonthYearCombo = it.getMonthYearCombo()
//                    (monthYearCombo != newMonthYearCombo).let {
//                        if (it) {
//                            monthYearCombo = newMonthYearCombo
//                            true
//                        } else {
//                            false
//                        }
//                    }
//                }

                partitionedScheduleList.forEach { partition ->
                    stickyHeader {
                        Text(text = partition.value.first().zonedDateTime.month.name)
                    }
                    items(
                        items = partition.value,
                        key = { schedule: Schedule -> schedule.uid },
                        itemContent = { schedule ->
                            Text(text = schedule.gameTime)
                        }
                    )
                }
            }
        )
    }
}

@Preview(device = Devices.DEFAULT)
@Composable
fun MainScreenPrev() {
    SchedulePage.MainScreen(
        modifier = Modifier.fillMaxSize(),
        viewModel = MainViewModel(
            scheduleList = listOf(),
            teamList = listOf()
        )
    )
}