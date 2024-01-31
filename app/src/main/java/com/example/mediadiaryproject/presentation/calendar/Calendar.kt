package com.example.mediadiaryproject.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.calendar.components.ContentItem
import com.example.mediadiaryproject.presentation.calendar.state.CalendarState
import com.example.mediadiaryproject.presentation.calendar.viewmodel.CalendarViewModel
import com.example.mediadiaryproject.presentation.dayslistscreen.state.CollectionState
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
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
                scope.launch {
                    val job = viewModel.getCreatedDay(collection = collection)

                    job.join()

                    val createdDayId = viewModel.createdDayId
                    navigateToDay(createdDayId)
                }
            }
        )

    }


}

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
            .padding(10.dp)
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun Header(
    data: CalendarState,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    Row {
        Text(
            text = if (data.selectedDate.isToday) {
                "Today"
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                )
            },
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp),
            textAlign = TextAlign.Start,
            fontSize = 20.sp
        )
        IconButton(onClick = { onPrevClickListener(data.startDate.date) }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Previous"
            )
        }
        IconButton(onClick = { onNextClickListener(data.endDate.date) }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Next"
            )
        }
    }

}

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


@RequiresApi(Build.VERSION_CODES.O)
@Preview()
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
