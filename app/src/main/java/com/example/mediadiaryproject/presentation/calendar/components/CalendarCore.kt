package com.example.mediadiaryproject.presentation.calendar.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.calendar.state.CalendarState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarCore(
    data: CalendarState,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
    selectDate: (date: CalendarState.Date) -> Unit
) {

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.White)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header(
            data,
            onPrevClickListener = { startDate ->
                onPrevClickListener(startDate)
            },
            onNextClickListener = { endDate ->
                onNextClickListener(endDate)
            })
        Content(data, onDateClickListener = { date ->
            selectDate(date)

        })
    }


}