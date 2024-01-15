package com.example.mediadiaryproject.presentation.dayslistscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.calendar.Calendar
import com.example.mediadiaryproject.presentation.dayslistscreen.state.CollectionState
import com.example.mediadiaryproject.presentation.dayslistscreen.viewmodel.DaysListScreenViewModel
import com.example.mediadiaryproject.presentation.navgraph.MediaDiaryNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RequiresApi(Build.VERSION_CODES.O)
@MediaDiaryNavGraph(start = true)
@Destination
@Composable
fun DaysListScreen(
    navigator: DestinationsNavigator,
    viewModel: DaysListScreenViewModel = hiltViewModel()
) {

    val collectionId = viewModel.collectionId

    Column {
        Column {

        }
        Button(onClick = { viewModel.toggleCalendar() }) {
            Text(text = "Add")
        }
        Column {
            if (viewModel.showCalendarState) {
                Calendar(
                    collection = CollectionState(
                        id = collectionId,
                        title = "My Test Collection",
                        activityId = 0
                    )
                )
            }
        }

    }


}

@Composable
private fun DayElement(date: String) {
    Text(text = date)
}

