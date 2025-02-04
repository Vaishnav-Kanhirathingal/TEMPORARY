package com.example.raw_eg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raw_eg.ui.home.SchedulePage
import com.example.raw_eg.ui.theme.RAWEGTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RAWEGTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MainScreen(
                            modifier = Modifier.padding(paddingValues = innerPadding),
                            viewModel = viewModel
                        )
                    }
                )
            }
        }
    }

    companion object {
        @Composable
        fun MainScreen(
            modifier: Modifier,
            viewModel: MainViewModel
        ) {
            val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
            Column(
                modifier = modifier,
                content = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        text = "TEAM",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,

                        )
                    val scope = rememberCoroutineScope()
                    TabRow(
                        modifier = Modifier,
                        selectedTabIndex = pagerState.currentPage,
                        tabs = {
                            HomeTabSelections.entries.forEach {
                                Tab(
                                    modifier = Modifier
                                        .heightIn(min = CustomSharedValues.Dims.minimumTouchSize)
                                        .align(alignment = Alignment.CenterHorizontally),
                                    selected = pagerState.currentPage == it.ordinal,
                                    onClick = { scope.launch { pagerState.scrollToPage(page = it.ordinal) } },
                                    content = {
                                        Text(
                                            modifier = Modifier,
                                            textAlign = TextAlign.Center,
                                            text = it.displayText
                                        )
                                    }
                                )
                            }
                        }
                    )

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        pageContent = { pageIndex: Int ->
                            when (pageIndex) {
                                0 -> SchedulePage.MainScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    viewModel = viewModel
                                )

                                1 -> Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                    content = {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center,
                                            text = "Games page"
                                        )
                                    }
                                )
                            }
                        }
                    )
                }
            )
        }
    }
}

enum class HomeTabSelections {
    SCHEDULE, GAMES;

    val displayText: String get() = this.name.lowercase().replaceFirstChar { it.uppercase() }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MainActivity.MainScreen(
        modifier = Modifier.fillMaxSize(),
        viewModel = MainViewModel(
            scheduleList = listOf(),
            teamList = listOf()
        )
    )
}