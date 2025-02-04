package com.example.raw_eg.data.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleTeam(
    @SerializedName("tid") val teamId: String,
    @SerializedName("re") val records: String,
    @SerializedName("ta") val teamAlias: String,
    @SerializedName("tn") val teamName: String,
    @SerializedName("tc") val teamCity: String,
    @SerializedName("s") val score: String,
    @SerializedName("ist_group") val istGroup: String,
)