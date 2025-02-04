package com.example.raw_eg.data.schedule

import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class Schedule(
    @SerializedName("uid") val uid: String,
    @SerializedName("year") val year: Int?,
    @SerializedName("league_id") val leagueId: String?,
    @SerializedName("season_id") val seasonId: String?,
    @SerializedName("h") val homeTeam: ScheduleTeam,
    @SerializedName("v") val visitorTeam: ScheduleTeam,
    @SerializedName("gametime") val gameTime: String,
    @SerializedName("stt") val statusTime: String,
    @SerializedName("arena_city") val arenaCity: String,
) {
    val gameTimeMillis: Long get() = Instant.parse(this.gameTime).toEpochMilli()
    val zonedDateTime: ZonedDateTime get() = Instant.parse(this.gameTime).atZone(ZoneId.of("UTC"))


    fun getCardTitle(): String {
        val cityStatus = (this.arenaCity == this.homeTeam.teamCity)
            .let { if (it) "HOME" else "AWAY" }

        val formattedDate = this.zonedDateTime.format(
            DateTimeFormatter.ofPattern(
                "EEE MMM dd"
            ).withLocale(java.util.Locale.ENGLISH)
        ).uppercase()

        val time = this.statusTime.let {
            if (it == "Final") it
            else it.replace(oldValue = " ET", newValue = "")
        }.uppercase()

        return "$cityStatus | $formattedDate | $time"
    }
}