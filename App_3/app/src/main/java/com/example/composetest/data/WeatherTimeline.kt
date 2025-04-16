package com.example.composetest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherTimeline(
    @SerialName("date") val date: String,
    @SerialName("temperature") val temperature: Int,
    @SerialName("weather_description") val weatherDescription: String,
    @SerialName("humidity") val humidity: Int,
    @SerialName("wind_speed") val windSpeed: Int,
)
/*
*
 {
  "date": "2023-07-28",
  "temperature": 24,
  "weather_description": "Partly cloudy",
  "humidity": 55,
  "wind_speed": 12
},
{
  "date": "2023-07-29",
  "temperature": 26,
  "weather_description": "Sunny",
  "humidity": 48,
  "wind_speed": 9
}
* */

