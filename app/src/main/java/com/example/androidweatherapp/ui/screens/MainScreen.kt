package com.example.androidweatherapp.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidweatherapp.data.CityWeather
import com.example.androidweatherapp.ui.components.CityItem
import com.example.androidweatherapp.viewmodel.CitiesWeatherViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: CitiesWeatherViewModel = viewModel()) {
    val citiesWeather = viewModel.citiesWeather.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
       viewModel.loadCitiesWeather(context)
    }

    // Log the weather data
    LaunchedEffect(citiesWeather) {
        Log.d("DEBUG WeatherData View", "Cities Weather: $citiesWeather")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "That is the Main Screen",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 16.dp),
            style = TextStyle(
                fontSize = 24.sp,         // Large text size
                fontWeight = FontWeight.Bold, // Bold text
                textAlign = TextAlign.Center // Center align text
            )
        )

        //list here
        if (citiesWeather.isEmpty()) {
            CircularProgressIndicator()  // ✅ Show a loading indicator while fetching data
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(citiesWeather) { weatherData ->
                    CityItem(
                        item = CityWeather(
                            city = weatherData.city,
                            descr = weatherData.weather.weather.firstOrNull()?.description
                                ?: "Unknown",
                            temp = weatherData.weather.main.temp,
                            icon = weatherData.weather.weather.firstOrNull()?.icon ?: "01d"
                        ),
                        isPressable = true,
                        onItemClick = { city ->
                            print("Clicked on $city")
                        }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
        MainScreen()
}


