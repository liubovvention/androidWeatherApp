package com.example.androidweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidweatherapp.ui.screens.MainScreen
import com.example.androidweatherapp.ui.theme.AndroidWeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            AndroidWeatherAppTheme {
                    MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    AndroidWeatherAppTheme {
        MainScreen()
    }
}