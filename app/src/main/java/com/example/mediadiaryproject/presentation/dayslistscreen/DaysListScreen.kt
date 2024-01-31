package com.example.mediadiaryproject.presentation.dayslistscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.calendar.Calendar
import com.example.mediadiaryproject.presentation.dayslistscreen.state.CollectionState
import com.example.mediadiaryproject.presentation.dayslistscreen.state.DayState
import com.example.mediadiaryproject.presentation.dayslistscreen.viewmodel.DaysListScreenViewModel
import com.example.mediadiaryproject.presentation.destinations.DayScreenWrapperDestination
import com.example.mediadiaryproject.presentation.navgraph.MediaDiaryNavGraph
import com.example.mediadiaryproject.ui.theme.AppBackgroundColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@MediaDiaryNavGraph(start = true)
@Destination
@Composable
fun DaysListScreen(
    navigator: DestinationsNavigator,
    viewModel: DaysListScreenViewModel = hiltViewModel()
) {

    val collectionTitle = "My collection"
    val days = viewModel.daysState.value
    val collectionId = 0

    LaunchedEffect(true) {
        viewModel.getDays()
    }

    Scaffold(
        topBar = { CollectionTitle(collectionTitle) },
//        bottomBar = {
//            Button(onClick = { viewModel.showCalendar() }) {
//                Text(text = "Add")
//            }
//        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DaysList(
                    days,
                    navigateToDay = { dayId -> navigator.navigate(DayScreenWrapperDestination(dayId = dayId)) })

                if (viewModel.showCalendarState) {
                    Calendar(
                        collection = CollectionState(
                            id = collectionId,
                            title = "My Test Collection",
                            activityId = 0
                        ),
                        navigateToDay = { id ->
                            navigator.navigate(DayScreenWrapperDestination(dayId = id))
                            viewModel.hideCalendar()
                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                } else {
                    FloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp),
                        onClick = { viewModel.showCalendar() }
                    ) {
                        Text("+")
                    }
                }


            }
        }


    }
}

@Composable
private fun CollectionTitle(title: String) {
    Text(text = title)
}

@Composable
private fun DaysList(
    days: List<DayState>,
    navigateToDay: (dayId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppBackgroundColor)
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        days.forEach { day ->
            DayItem(date = day.date, navigateToDay = { navigateToDay(day.id) })
        }
    }

}

@Composable
private fun DayItem(date: String, navigateToDay: () -> Unit) {
    val cornerSize = 20.dp

    Column(modifier = Modifier.padding(vertical = 5.dp).clickable { navigateToDay() }) {
        Column(
            modifier = Modifier.shadow(15.dp, shape = RoundedCornerShape(cornerSize), clip = true),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(cornerSize))
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = date, fontSize = 24.sp)
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun DaysListPreview() {
    val listOfDays = listOf(
        DayState(id = 0, date = "12 January, 2024"),
        DayState(id = 2, date = "26 January, 2024"),
        DayState(id = 5, date = "28 January, 2024"),
        DayState(id = 7, date = "30 January, 2024"),
        DayState(id = 8, date = "31 January, 2024"),
    )
    DaysList(days = listOfDays, navigateToDay = {})
}


@Preview
@Composable
fun DayItemPreview() {
    DayItem(date = "31 January, 2024", navigateToDay = {})
}

