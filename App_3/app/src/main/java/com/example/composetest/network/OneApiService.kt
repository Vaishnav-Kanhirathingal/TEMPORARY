package com.example.composetest.network

import com.example.composetest.data.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface OneApiService {
    /**
     * https://www.freetestapi.com/api/v1/weathers
     * https://www.freetestapi.com/api/v1/weathers/{id}
     * */
    companion object {
        private const val BASE_URL = "https://www.freetestapi.com/api/v1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OneApiService::class.java)

    }

    @GET("weathers/")
    suspend fun getWeatherList(): List<Weather>

    @GET("weathers/{id}/")
    suspend fun getWeather(@Path("id") id: Int): Weather

}