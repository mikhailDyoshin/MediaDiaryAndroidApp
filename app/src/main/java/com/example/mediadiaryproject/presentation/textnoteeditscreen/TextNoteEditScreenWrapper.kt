package com.example.mediadiaryproject.presentation.textnoteeditscreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.textnoteeditscreen.viewmodel.TextNoteEditScreenViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun TextNoteEditScreenWrapper(
    textNoteToEditId: Int = -1,
    navigator: DestinationsNavigator,
    viewModel: TextNoteEditScreenViewModel = hiltViewModel(),
    dayId: Int,
) {


}