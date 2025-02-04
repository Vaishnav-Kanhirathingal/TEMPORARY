package com.example.raw_eg.ui.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.raw_eg.CustomSharedValues
import com.example.raw_eg.MainViewModel
import com.example.raw_eg.data.schedule.Schedule
import com.example.raw_eg.data.schedule.ScheduleTeam

object SchedulePage {
    private val TAG = this::class.simpleName

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
            modifier = modifier.background(color = MaterialTheme.colorScheme.surfaceContainer),
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

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun ScheduleItem(
        modifier: Modifier,
        viewModel: MainViewModel,
        schedule: Schedule,
    ) {
        Card(
            modifier = modifier.padding(horizontal = 16.dp),
            content = {
                val isFinal = (schedule.statusTime == "Final")

                Column(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    content = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = schedule.getCardTitle()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            content = {
                                @Composable
                                fun TeamColumn(
                                    modifier: Modifier,
                                    team: ScheduleTeam
                                ) {
                                    Column(
                                        modifier = modifier,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        content = {
                                            GlideImage(
                                                modifier = Modifier.size(size = 100.dp),
                                                model = viewModel.teamList
                                                    .find { it.teamId == team.teamId }
                                                    ?.logoURL
                                                    .apply { Log.d(TAG, "url = $this") },
                                                contentDescription = null,
                                            )
                                            if (isFinal) {
                                                Text(
                                                    style = TextStyle(fontWeight = FontWeight.Bold),
                                                    fontSize = 24.sp,
                                                    text = team.teamAlias
                                                )
                                            }
                                        }
                                    )
                                }

                                @Composable
                                fun TeamMidTitle(
                                    modifier: Modifier,
                                    team: ScheduleTeam
                                ) {
                                    Text(
                                        modifier = modifier,
                                        style = TextStyle(fontWeight = if (isFinal) FontWeight.SemiBold else FontWeight.Bold),
                                        fontSize = 28.sp,
                                        textAlign = TextAlign.Center,
                                        text = team.let { if (isFinal) it.score else it.teamAlias }
                                    )
                                }
                                //--------------------------------------------------------------left
                                TeamColumn(
                                    modifier = Modifier.weight(1f),
                                    team = schedule.visitorTeam
                                )

                                TeamMidTitle(
                                    modifier = Modifier.weight(1f),
                                    team = schedule.visitorTeam
                                )
                                //---------------------------------------------------------------mid
                                Text(
                                    modifier = Modifier.padding(horizontal = 24.dp),
                                    style = TextStyle(fontWeight = FontWeight.Medium),
                                    fontSize = 20.sp,
                                    text = if (isFinal) "@" else "VS"
                                )
                                //-------------------------------------------------------------right
                                TeamMidTitle(
                                    modifier = Modifier.weight(1f),
                                    team = schedule.homeTeam
                                )
                                TeamColumn(
                                    modifier = Modifier.weight(1f),
                                    team = schedule.homeTeam
                                )
                            }
                        )
                        if (!isFinal) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                onClick = { TODO() },
                                content = {
                                    Text(text = "BUY TICKETS ON TICKETMASTER")
                                }
                            )
                        }
                    }
                )
            }
        )
    }
}