package com.example.raw_eg.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.raw_eg.MainViewModel

object SchedulePage {
    @Composable
    fun MainScreen(
        modifier: Modifier,
        viewModel: MainViewModel
    ) {
//        Text(text = "page 1 ${viewModel.scheduleList.first().uid}")
//        Text(text = "page 1 ${viewModel.teamList.first().uid}")
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