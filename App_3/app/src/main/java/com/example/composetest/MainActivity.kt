package com.example.composetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetest.network.OneApiService
import com.example.composetest.ui.theme.ComposeTestTheme
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG = this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MainNavHost(
                            modifier = Modifier.padding(paddingValues = innerPadding)
                        )
                    }
                )
            }
        }

        lifecycleScope.launch {
            OneApiService.retrofit.getWeatherList().let {
                Log.d(TAG, "list = ${GsonBuilder().setPrettyPrinting().create().toJson(it)}")
            }
            OneApiService.retrofit.getWeather(id = 1).let {
                Log.d(TAG, "weather 1 = ${GsonBuilder().setPrettyPrinting().create().toJson(it)}")
            }
        }
    }

    @Composable
    fun MainNavHost(
        modifier: Modifier
    ) {
        val navController = rememberNavController()
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = MainRoutes.MainScreen,
            builder = {
                composable<MainRoutes.MainScreen>(
                    content = {
                        Text(text = "Working")
                    }
                )
            }
        )
    }
}

sealed class MainRoutes {

    @Serializable
    data object MainScreen : MainRoutes()
}