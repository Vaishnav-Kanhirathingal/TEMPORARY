package com.example.raw_eg.data.schedule

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("uid") val uid: String,
    @SerializedName("year") val year: Int?,
    @SerializedName("league_id") val leagueId: String?,
    @SerializedName("season_id") val seasonId: String?,
    @SerializedName("h") val homeTeam: ScheduleTeam?,
    @SerializedName("v") val visitorTeam: ScheduleTeam?,
    @SerializedName("gametime") val gameTime: String?
)