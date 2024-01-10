package com.example.mediadiaryproject.presentation.textnotescreen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.textnotescreen.viewmodel.TextNoteScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TextNoteScreen(
    navigator: DestinationsNavigator,
    viewModel: TextNoteScreenViewModel = hiltViewModel()
) {

    Column {
        Text(text = "Text note screen")
        Text(text = viewModel.date)
        TextField(
            value = viewModel.title,
            onValueChange = { title -> viewModel.updateTitle(title) }
        )
        TextField(
            value = viewModel.text,
            onValueChange = { text -> viewModel.updateText(text) },
            maxLines = 5
        )
    }

}
