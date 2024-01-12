package com.example.mediadiaryproject.presentation.calendar.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mediadiaryproject.presentation.calendar.state.CalendarState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(
        CalendarState(
            selectedDate = CalendarState.Date(
                date = LocalDate.now(),
                isSelected = true,
                isToday = true
            ),
            startDate = CalendarState.Date(
                date = LocalDate.now(),
                isSelected = true,
                isToday = true
            ),
            endDate = CalendarState.Date(
                date = LocalDate.now(),
                isSelected = true,
                isToday = true
            ),
            visibleDates = listOf()
        )
    )

    init {
        getData(lastSelectedDate = today)
    }

    private val today: LocalDate
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return LocalDate.now()
        }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate) {
        val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
        val endDayOfWeek = firstDayOfWeek.plusDays(7)
        val visibleDates = getDatesBetween(firstDayOfWeek, endDayOfWeek)
        toUiModel(visibleDates, lastSelectedDate)
    }

    fun selectDate(date: CalendarState.Date) {
        state = state.copy(
            selectedDate = date,
            visibleDates = state.visibleDates.map {
                it.copy(
                    isSelected = it.date.isEqual(date.date)
                )
            })
    }

    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays(/* daysToAdd = */ 1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }

    private fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate
    ) {
        val datesList = dateList.map {
            toItemUiModel(it, it.isEqual(lastSelectedDate))
        }

        state =  CalendarState(
            selectedDate = toItemUiModel(lastSelectedDate, true),
            visibleDates = datesList,
            startDate = datesList.first(),
            endDate = datesList.last(),
        )
    }

    private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = CalendarState.Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date,
    )

}