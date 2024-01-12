package com.example.mediadiaryproject.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.calendar.state.CalendarState
import com.example.mediadiaryproject.presentation.calendar.viewmodel.CalendarViewModel
import com.example.mediadiaryproject.presentation.dayscreen.MAIN_SCREEN_ROUTE
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@RequiresApi(Build.VERSION_CODES.O)
@Destination(route = MAIN_SCREEN_ROUTE)
@Composable
fun Calendar(
    navigator: DestinationsNavigator,
    viewModel: CalendarViewModel = hiltViewModel()
) {

    val data = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {

        Header(
            data,
            onPrevClickListener = { startDate ->
                val finalStartDate = startDate.minusDays(1)
                viewModel.getData(finalStartDate, viewModel.state.selectedDate.date)
            },
            onNextClickListener = { endDate ->
                val finalStartDate =
                    endDate.plusDays(2)
                viewModel.getData(finalStartDate, viewModel.state.selectedDate.date)
            })
        Content(data, onDateClickListener = { date -> viewModel.selectDate(date) })
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
        for (date in dates) {
            ContentItem(date = date, onDateClickListener)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(date: CalendarState.Date, onClickListener: (CalendarState.Date) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable { // making the element clickable, by adding 'clickable' modifier
                onClickListener(date)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun CalendarPreview() {

//    Calendar()
}