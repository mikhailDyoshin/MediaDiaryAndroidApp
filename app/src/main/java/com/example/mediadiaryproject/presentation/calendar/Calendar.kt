package com.example.mediadiaryproject.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.calendar.components.CalendarCore
import com.example.mediadiaryproject.presentation.calendar.state.CalendarState
import com.example.mediadiaryproject.presentation.calendar.viewmodel.CalendarViewModel
import com.example.mediadiaryproject.presentation.dayslistscreen.state.CollectionState
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    collection: CollectionState,
    viewModel: CalendarViewModel = hiltViewModel(),
    navigateToDay: (id: Int) -> Unit,
    modifier: Modifier
) {

    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {

        CalendarCore(data = viewModel.state, onPrevClickListener = { startDate ->
            val finalStartDate = startDate.minusDays(1)
            viewModel.getData(finalStartDate, viewModel.state.selectedDate.date)
        }, onNextClickListener = { endDate ->
            val finalStartDate =
                endDate.plusDays(2)
            viewModel.getData(finalStartDate, viewModel.state.selectedDate.date)
        },
            selectDate = { date ->
                viewModel.selectDate(date)
                if (date.isSelected) {
                    scope.launch {
                        val job = viewModel.getCreatedDay(collection = collection)

                        job.join()

                        val createdDayId = viewModel.createdDayId

                        navigateToDay(createdDayId)
                    }
                }

            }
        )

    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CalendarPreview() {

    val startDate = LocalDate.now()
    val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
    val endDayOfWeek = firstDayOfWeek.plusDays(7)
    val numOfDays = ChronoUnit.DAYS.between(firstDayOfWeek, endDayOfWeek)
    val daysBetween = Stream.iterate(firstDayOfWeek) { date ->
        date.plusDays(1)
    }
        .limit(numOfDays)
        .collect(Collectors.toList())


    val datesList = daysBetween.map { date ->
        CalendarState.Date(
            isSelected = date.isEqual(LocalDate.now()),
            isToday = date.isEqual(startDate),
            date = date,
        )
    }

    val firstDate = datesList[0]
    val endDate = datesList.last()

    CalendarCore(
        data = CalendarState(
            selectedDate = CalendarState.Date(
                date = startDate,
                isSelected = true,
                isToday = true
            ), visibleDates = datesList, startDate = firstDate, endDate = endDate
        ),
        onPrevClickListener = {},
        onNextClickListener = {},
        selectDate = {}
    )
}
