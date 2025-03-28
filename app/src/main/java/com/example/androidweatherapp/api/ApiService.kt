package com.example.androidweatherapp.api

import com.example.androidweatherapp.data.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object APIConfig {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = "ef7547eb05ea5d4514dc2aa20c325dbb"
}

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = APIConfig.API_KEY
    ): WeatherResponse
}

object ApiService {
    // Create Retrofit instance with Gson converter
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(APIConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Create an instance of the API interface
    val weatherApi: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    // Function to fetch weather data
    //fun getWeather(city: String) =
    //    weatherApi.getWeather(city)
}

// api call
// val weatherCall = ApiService.getWeather("London")