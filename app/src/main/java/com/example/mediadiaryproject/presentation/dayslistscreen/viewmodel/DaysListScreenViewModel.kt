package com.example.mediadiaryproject.presentation.dayslistscreen.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.domain.models.CollectionModel
import com.example.mediadiaryproject.domain.usecase.GetDaysByCollectionUseCase
import com.example.mediadiaryproject.domain.usecase.GetListOfMediaUseCase
import com.example.mediadiaryproject.presentation.dayslistscreen.state.DayState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject

@HiltViewModel
class DaysListScreenViewModel @Inject constructor(private val getDaysByCollectionUseCase: GetDaysByCollectionUseCase) :
    ViewModel() {

    var showCalendarState by mutableStateOf(false)
        private set

    var daysState: MutableState<List<DayState>> = mutableStateOf(listOf())
        private set

    var collectionIdState = 0


//    fun toggleCalendar() {
//        showCalendarState = !showCalendarState
//    }

    fun showCalendar() {
        showCalendarState = true
    }

    fun hideCalendar() {
        showCalendarState = false
    }

    fun getDays(collectionId: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfDays = getDaysByCollectionUseCase.execute(collectionId = collectionId)
                .map { DayState(id = it.id, date = it.date) }
            daysState.value = listOfDays
        }

    }

}