package com.example.raw_eg

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.raw_eg.data.schedule.Schedule
import com.example.raw_eg.data.teams.Team
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named(DatabaseModule.SCHEDULE_LIST) val scheduleList: List<Schedule>,
    @Named(DatabaseModule.TEAM_LIST) val teamList: List<Team>
) : ViewModel() {
    private val TAG = this::class.simpleName

    fun getMonthPartitionedScheduleList(): List<List<Schedule>> {
        return this.scheduleList
            .groupBy { it.zonedDateTime.let { zdt: ZonedDateTime -> "${zdt.month.value} - ${zdt.year}" } }
            .map { it.value.reversed() }
            .sortedBy { it.first().gameTimeMillis }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private val TAG = this::class.simpleName

    const val SCHEDULE_LIST = "SCHEDULE_LIST"
    const val TEAM_LIST = "TEAM_LIST"

    @Provides
    @Singleton
    @Named(SCHEDULE_LIST)
    fun providesScheduleList(
        @ApplicationContext context: Context
    ): List<Schedule> {
//        try {
        return Gson().fromJson(
            JsonParser.parseString(
                BufferedReader(
                    InputStreamReader(
                        context.resources.openRawResource(R.raw.schedule)
                    )
                ).readText()
            )
                .asJsonObject.get("data")
                .asJsonObject.get("schedules")
                .asJsonArray.toString(),
            object : TypeToken<List<Schedule>>() {}
        )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            return listOf()
//        }
    }

    @Provides
    @Singleton
    @Named(TEAM_LIST)
    fun providesTeamList(
        @ApplicationContext context: Context
    ): List<Team> {
        return try {
            Gson().fromJson(
                JsonParser.parseString(
                    BufferedReader(
                        InputStreamReader(
                            context.resources.openRawResource(R.raw.teams)
                        )
                    ).readText()
                )
                    .asJsonObject.get("data")
                    .asJsonObject.get("teams")
                    .asJsonArray.toString(),
                object : TypeToken<List<Team>>() {}
            )
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }
}