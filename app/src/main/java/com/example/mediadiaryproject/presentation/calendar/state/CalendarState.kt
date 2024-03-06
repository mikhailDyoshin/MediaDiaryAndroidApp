package com.example.mediadiaryproject.presentation.calendar.state

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CalendarState(
    var selectedDate: Date,
    val visibleDates: List<Date>,
    var startDate: Date,
    var endDate: Date,
) {

//    val startDate: Date = visibleDates.first() // the first of the visible dates
//    val endDate: Date = visibleDates.last() // the last of the visible dates

    data class Date(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        val day: String = date.format(DateTimeFormatter.ofPattern("E")) // get the day by formatting the date
    }
}