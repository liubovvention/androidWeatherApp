package com.example.androidweatherapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidweatherapp.R
import com.example.androidweatherapp.api.ApiService
import com.example.androidweatherapp.data.WeatherResponse
import com.example.androidweatherapp.services.UtilityService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CitiesWeatherViewModel : ViewModel() {
    private val _citiesWeather = MutableStateFlow<List<WeatherData>>(emptyList())
    val citiesWeather: StateFlow<List<WeatherData>> = _citiesWeather.asStateFlow()

    fun loadCitiesWeather(context: Context) {
        viewModelScope.launch {
            val cities = UtilityService.loadJsonFromRaw(context, R.raw.cities_list)
            val weatherList = cities.mapNotNull { city ->
                try {
                    val weather = ApiService.weatherApi.getWeather(city.toString())
                    WeatherData(city.toString(), weather)
                } catch (e: Exception) {
                    null // Handle API errors
                }
            }
            _citiesWeather.value = weatherList
        }
    }
}

data class WeatherData(val city: String, val weather: WeatherResponse)