package com.example.mediadiaryproject.presentation.daycontentscreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.daycontentscreen.viewmodel.DayContentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DayContentScreen(
    navigator: DestinationsNavigator,
    viewModel: DayContentViewModel = hiltViewModel(),
    dayId: Int,
) {

    LaunchedEffect(true) {
        viewModel.getNotesByDay(dayId)
    }

    val state = viewModel.state.value

    DayContentScreenCore(
        state = state,
        openTextNote = { id -> Log.d("Open text note", "Text note was opened, id: $id") })

}
