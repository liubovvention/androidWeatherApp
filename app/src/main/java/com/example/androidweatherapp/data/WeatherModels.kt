package com.example.androidweatherapp.data
import com.google.gson.annotations.SerializedName

data class CityWeather(
    val city: String,
    val icon: String,
    val descr: String,
    val temp: Double,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val wspeed: Double = 0.0,
    val cloud: Int = 0
)

data class WeatherResponse(
    val coord: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: MainWeather,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain?,
    val clouds: Clouds,
    val dt: Int,
    val sys: SystemInfo,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainWeather(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("sea_level") val seaLevel: Int?,
    @SerializedName("grnd_level") val grndLevel: Int?
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double?
)

data class Rain(
    @SerializedName("1h") val oneHour: Double?
)

data class Clouds(
    val all: Int
)

data class SystemInfo(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)
