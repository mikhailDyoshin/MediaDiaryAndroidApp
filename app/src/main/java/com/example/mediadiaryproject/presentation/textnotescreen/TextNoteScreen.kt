package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.textnotescreen.viewmodel.TextNoteScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TextNoteScreen(
    navigator: DestinationsNavigator,
    viewModel: TextNoteScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val date = state.date


    Column {
        Text(text = "Text note screen")
        Text(text = date)
    }
}