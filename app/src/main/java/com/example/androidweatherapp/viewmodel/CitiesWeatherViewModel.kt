package com.example.androidweatherapp.viewmodel

import android.content.Context
import android.util.Log
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
            //Log.d("DEBUG Model", "Cities List: $cities")
            val weatherList = cities.mapNotNull { city ->
                try {
                    Log.d("DEBUG Model", "City API call: $city")
                    val response = ApiService.weatherApi.getWeather(city)

                    if (response.isSuccessful) {
                        response.body()?.let { weather ->
                            Log.d("DEBUG Model", "Response success: $city - $weather")
                            WeatherData(city, weather)
                        }
                    } else {
                        Log.e(
                            "DEBUG Model",
                            "API call failed: $city - HTTP ${response.code()} - ${
                                response.errorBody()?.string()
                            }"
                        )
                        null
                    }
                } catch (e: Exception) {
                    Log.e("DEBUG Model", "API call error: $city, $e", e)
                    null
                }
            }
            Log.d("DEBUG Model", "Cities Weather List: $weatherList")
            _citiesWeather.value = weatherList
        }
    }
}


data class WeatherData(val city: String, val weather: WeatherResponse)