package com.example.androidweatherapp.services

import android.content.Context

object UtilityService {
    fun loadJsonFromRaw(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    }

    // Convert Kelvin to Fahrenheit (with two decimal places)
    fun kelvinToFahrenheit(kelvin: Double): String {
        return String.format("%.2f", (kelvin - 273.15) * 9 / 5 + 32)
    }
}

//call func: open the raw resource file, read it, and return the content as a string
// val cities = UtilityService.loadJsonFromRaw(context, R.raw.cities_list)
// val json = UtilityService.loadJsonFromRaw(context, R.raw.user)