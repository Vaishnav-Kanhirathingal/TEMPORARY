package com.example.assignment.api

import com.example.assignment.data.ListScreen
import com.example.assignment.data.UserData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val baseURL = "https://aspireinfotechs.in/filmdial_app/public/api/auth/"

private val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(baseURL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service: AspireApi by lazy { retrofit.create(AspireApi::class.java) }

interface AspireApi {
    @POST("register")
    fun register(@Body userData: UserData): Call<UserData>

    @GET("get_department")
    suspend fun getDepartment(): ListScreen
}