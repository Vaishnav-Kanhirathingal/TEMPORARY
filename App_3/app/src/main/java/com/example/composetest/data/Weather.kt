package com.example.composetest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "city") val city: String,
    @SerialName(value = "country") val country: String,
    @SerialName(value = "latitude") val latitude: Float,
    @SerialName(value = "longitude") val longitude: Float,
    @SerialName(value = "temperature") val temperature: Int,
    @SerialName(value = "weather_description") val weatherDescription: String,
    @SerialName(value = "humidity") val humidity: Int,
    @SerialName(value = "wind_speed") val windSpeed: Int,
    @SerialName(value = "forecast") val forecast: List<WeatherTimeline>,
)

/*
*
{
                                                                                "id": 1,
                                                                                "city": "New York",
                                                                                "country": "United States",
                                                                                "latitude": 40.7128,
                                                                                "longitude": -74.006,
                                                                                "temperature": 25,
                                                                                "weather_description": "Clear sky",
                                                                                "humidity": 50,
                                                                                "wind_speed": 10,
                                                                                "forecast": [
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
                                                                                ]
                                                                              }
*
* */