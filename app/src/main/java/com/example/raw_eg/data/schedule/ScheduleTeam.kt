package com.example.raw_eg.data.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleTeam(
    @SerializedName("tid") val teamId: String,
    @SerializedName("re") val re: String,
    @SerializedName("ta") val ta: String,
    @SerializedName("tn") val tn: String,
    @SerializedName("tc") val tc: String,
    @SerializedName("s") val s: String,
    @SerializedName("ist_group") val ist_group: String,
)