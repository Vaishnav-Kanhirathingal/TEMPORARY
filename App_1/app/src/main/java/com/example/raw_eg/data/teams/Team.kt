package com.example.raw_eg.data.teams

import com.google.gson.annotations.SerializedName

class Team(
    @SerializedName("uid") val uid: String,
    @SerializedName("year") val year: String,
    @SerializedName("tid") val teamId: String,
    @SerializedName("tn") val tn: String,
    @SerializedName("ta") val ta: String,
    @SerializedName("logo") val logoURL: String,
)