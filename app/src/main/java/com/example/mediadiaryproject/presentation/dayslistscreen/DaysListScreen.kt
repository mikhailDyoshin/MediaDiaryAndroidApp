package com.example.mediadiaryproject.presentation.dayslistscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.calendar.Calendar
import com.example.mediadiaryproject.presentation.dayslistscreen.state.CollectionState
import com.example.mediadiaryproject.presentation.dayslistscreen.state.DayState
import com.example.mediadiaryproject.presentation.dayslistscreen.viewmodel.DaysListScreenViewModel
import com.example.mediadiaryproject.presentation.destinations.DayScreenDestination
import com.example.mediadiaryproject.presentation.navgraph.MediaDiaryNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DaysList(days, innerPadding)

                if (viewModel.showCalendarState) {
                    Calendar(
                        collection = CollectionState(
                            id = collectionId,
                            title = "My Test Collection",
                            activityId = 0
                        ),
                        navigateToDay = { id ->
                            navigator.navigate(DayScreenDestination(dayId = id))
                            viewModel.hideCalendar()
                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                } else {
                    FloatingActionButton(
                        modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
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
private fun DaysList(days: List<DayState>, padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (day in days) {
            DayItem(date = day.date)
        }
    }

}

@Composable
private fun DayItem(date: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = date)
    }

}

