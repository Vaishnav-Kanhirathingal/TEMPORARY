package com.example.raw_eg.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.raw_eg.CustomSharedValues
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            content = {
                viewModel.getMonthPartitionedScheduleList().forEach { partition ->
                    stickyHeader {
                        StickyHeader(
                            modifier = Modifier.fillMaxWidth(),
                            text = partition.first().zonedDateTime.let { "${it.month.name.uppercase()} ${it.year}" }
                        )
                    }
                    items(
                        items = partition,
                        key = { schedule: Schedule -> schedule.uid },
                        itemContent = { schedule ->
                            ScheduleItem(
                                modifier = Modifier.fillMaxWidth(),
                                viewModel = viewModel,
                                schedule = schedule
                            )
                        }
                    )
                }
            }
        )
    }

    @Composable
    private fun StickyHeader(
        modifier: Modifier,
        text: String
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = {
                IconButton(
                    modifier = Modifier
                        .sizeIn(
                            minWidth = CustomSharedValues.Dims.minimumTouchSize,
                            minHeight = CustomSharedValues.Dims.minimumTouchSize
                        ),
                    onClick = { TODO() },
                    content = {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }
                )
                Text(text = text)
                IconButton(
                    modifier = Modifier
                        .sizeIn(
                            minWidth = CustomSharedValues.Dims.minimumTouchSize,
                            minHeight = CustomSharedValues.Dims.minimumTouchSize
                        ),
                    onClick = { TODO() },
                    content = {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                )
            }
        )
    }

    @Composable
    private fun ScheduleItem(
        modifier: Modifier,
        viewModel: MainViewModel,
        schedule: Schedule,
    ) {
        Card(
            modifier = modifier,
            content = {
                val isFinal = (schedule.statusTime == "Final")

                Column(
                    modifier = Modifier.padding(all = 16.dp),
                    content = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = schedule.getCardTitle()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    content = {

                                    }
                                )


                                Text(text = if (isFinal) "@" else "VS")
                            }
                        )





                        Text(text = schedule.gameTime)
                        Text("game = ${schedule.visitorTeam.score} to ${schedule.homeTeam.score}")
                    }
                )
            }
        )
    }
}