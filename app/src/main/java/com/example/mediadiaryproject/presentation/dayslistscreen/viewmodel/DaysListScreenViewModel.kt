package com.example.mediadiaryproject.presentation.dayslistscreen.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mediadiaryproject.domain.usecase.GetListOfMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class DaysListScreenViewModel @Inject constructor() : ViewModel() {

    var showCalendarState by mutableStateOf(false)
        private set

    val collectionId = 0

    fun toggleCalendar() {
        showCalendarState = !showCalendarState
    }

}