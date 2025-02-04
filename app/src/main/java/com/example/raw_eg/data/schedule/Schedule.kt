package com.example.raw_eg.data.schedule

import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class Schedule(
    @SerializedName("uid") val uid: String,
    @SerializedName("year") val year: Int?,
    @SerializedName("league_id") val leagueId: String?,
    @SerializedName("season_id") val seasonId: String?,
    @SerializedName("h") val homeTeam: ScheduleTeam?,
    @SerializedName("v") val visitorTeam: ScheduleTeam?,
    @SerializedName("gametime") val gameTime: String
) {
    val gameTimeMillis: Long get() = Instant.parse(this.gameTime).toEpochMilli()
    val zonedDateTime: ZonedDateTime get() = Instant.parse(this.gameTime).atZone(ZoneId.of("UTC"))
}