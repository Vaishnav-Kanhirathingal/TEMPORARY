package com.example.raw_eg

import android.content.Context
import com.example.raw_eg.data.schedule.Schedule
import com.example.raw_eg.data.teams.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Provides
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    @Named(DatabaseModule.SCHEDULE_LIST) val scheduleList: List<Schedule>,
    @Named(DatabaseModule.TEAM_LIST) val teamList: List<Team>
) {
}

object DatabaseModule {

    const val SCHEDULE_LIST = "SCHEDULE_LIST"
    const val TEAM_LIST = "TEAM_LIST"

    @Provides
    @Named(SCHEDULE_LIST)
    fun providesScheduleList(
        context: Context
    ): List<Schedule> {
        return try {
            Gson().fromJson(
                BufferedReader(
                    InputStreamReader(
                        context.resources.openRawResource(R.raw.schedule)
                    )
                )
                    .readText(),
                object : TypeToken<List<Schedule>>() {}
            )
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }

    @Provides
    @Named(TEAM_LIST)
    fun providesTeamList(
        context: Context
    ): List<Team> {
        return try {
            Gson().fromJson(
                BufferedReader(
                    InputStreamReader(
                        context.resources.openRawResource(R.raw.schedule)
                    )
                )
                    .readText(),
                object : TypeToken<List<Team>>() {}
            )
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }
}