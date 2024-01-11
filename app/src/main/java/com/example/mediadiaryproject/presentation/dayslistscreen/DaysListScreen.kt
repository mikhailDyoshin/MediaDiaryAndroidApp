package com.example.mediadiaryproject.presentation.dayslistscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.dayslistscreen.viewmodel.DaysListScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DaysListScreen(
    navigator: DestinationsNavigator,
    viewModel: DaysListScreenViewModel = hiltViewModel()
) {

    Column {
        Column {
            
        }
        Button(onClick = { /*TODO*/ }) {
            
        }
    }
    

}

@Composable
private fun DayElement(date: String) {
    Text(text = date)
}

