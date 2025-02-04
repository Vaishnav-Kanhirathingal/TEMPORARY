package com.example.raw_eg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raw_eg.ui.theme.RAWEGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RAWEGTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MainScreen(
                            modifier = Modifier.padding(paddingValues = innerPadding)
                        )
                    }
                )
            }
        }
    }

    companion object {
        @Composable
        fun MainScreen(
            modifier: Modifier
        ) {
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
                    val tabSelected = remember { mutableStateOf(HomeTabSelections.SCHEDULE) }
                    TabRow(
                        modifier = Modifier,
                        selectedTabIndex = tabSelected.value.ordinal,
                        tabs = {
                            HomeTabSelections.entries.forEach {
                                Tab(
                                    modifier = Modifier
                                        .heightIn(min = CustomSharedValues.Dims.minimumTouchSize)
                                        .align(alignment = Alignment.CenterHorizontally),
                                    selected = tabSelected.value == it,
                                    onClick = { tabSelected.value = it },
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
    MainActivity.MainScreen(modifier = Modifier.fillMaxSize())
}