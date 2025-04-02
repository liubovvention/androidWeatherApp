package com.example.androidweatherapp.services

import android.content.Context
import android.util.Log
import org.json.JSONArray

object UtilityService {
    fun loadOldJsonFromRaw(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    }

    fun loadJsonFromRaw(context: Context, resId: Int): List<String> {
        val inputStream = context.resources.openRawResource(resId)
        val jsonText = inputStream.bufferedReader().use { it.readText() }

        return try {
            val jsonArray = JSONArray(jsonText)
            List(jsonArray.length()) { index -> jsonArray.getString(index) }
        } catch (e: Exception) {
            Log.e("UtilityService", "Error parsing JSON", e)
            emptyList()
        }
    }

    // Convert Kelvin to Fahrenheit (with two decimal places)
    fun kelvinToFahrenheit(kelvin: Double): String {
        return String.format("%.2f", (kelvin - 273.15) * 9 / 5 + 32)
    }
}

//call func: open the raw resource file, read it, and return the content as a string
// val cities = UtilityService.loadJsonFromRaw(context, R.raw.cities_list)
// val json = UtilityService.loadJsonFromRaw(context, R.raw.user)