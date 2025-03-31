package com.example.androidweatherapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.androidweatherapp.data.CityWeather
import com.example.androidweatherapp.services.UtilityService

@Composable
fun CityItem(
    item: CityWeather,
    isPressable: Boolean = false,
    onItemClick: (CityWeather) -> Unit = {}
) {
    val context = LocalContext.current
    val temperature = UtilityService.kelvinToFahrenheit(item.temp)

    // CityItem layout
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(enabled = isPressable) { onItemClick(item) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Weather icon
            val iconUrl = "https://openweathermap.org/img/wn/${item.icon}.png"
            Image(
                painter = rememberAsyncImagePainter(iconUrl),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(50.dp) // Adjust size as needed
            )

            Spacer(modifier = Modifier.width(8.dp))

            // City description
            Column(modifier = Modifier.weight(3f)) {
                Text(text = item.city, style = MaterialTheme.typography.bodyLarge)
                Text(text = item.descr, style = MaterialTheme.typography.bodyMedium)
            }

            // Temperature
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${temperature}°F",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }

            // Action button (pressable)
            if (isPressable) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(onClick = { onItemClick(item) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward, // Right arrow icon
                            contentDescription = "Right Arrow",
                            tint = Color.Blue
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CityItemWithToast(
    item: CityWeather,
    isPressable: Boolean = true,
    onItemClick: (CityWeather) -> Unit = {}
) {
    val context = LocalContext.current  // Get context

    // Handle the item click event and show toast inside LaunchedEffect
    LaunchedEffect(item) {
        Toast.makeText(context, "Clicked: ${item.city}", Toast.LENGTH_SHORT).show()
    }

    // CityItem UI with the given item and action callback
    CityItem(
        item = item,
        isPressable = isPressable,
        onItemClick = onItemClick
    )
}

// Preview for testing the UI in the Compose Preview
@Composable
@Preview
fun PreviewCityItem() {
    CityItemWithToast(
        item = CityWeather(
            city = "New York",
            descr = "Clear sky",
            temp = 293.15,
            icon = "01d"
        ),
        isPressable = true,
        onItemClick = { city ->
            // You can also do additional logic here if needed
        }
    )
}
