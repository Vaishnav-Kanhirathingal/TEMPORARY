package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetest.ui.theme.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
    }

    @Composable
    fun MainNavHost(
        modifier: Modifier
    ) {
        val navController = rememberNavController()
        NavHost(
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