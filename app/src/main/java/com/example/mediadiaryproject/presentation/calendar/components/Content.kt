package com.example.mediadiaryproject.presentation.calendar.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.calendar.state.CalendarState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(data: CalendarState, onDateClickListener: (CalendarState.Date) -> Unit) {

    val dates = data.visibleDates

    Row {
        dates.forEach { date ->
            ContentItem(date = date, onDateClickListener)
        }
    }

}