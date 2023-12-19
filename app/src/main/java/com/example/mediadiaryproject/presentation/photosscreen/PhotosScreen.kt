package com.example.mediadiaryproject.presentation.photosscreen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.photosscreen.viewmodel.PhotosScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PhotosScreen(
    navigator: DestinationsNavigator,
    viewModel: PhotosScreenViewModel = hiltViewModel()
) {

    val context: Context = LocalContext.current

    val listOfPhotos = viewModel.state.value

    Column {
        Column {
            for (file in listOfPhotos) {
                Text(file.fileName)
            }
        }
        Text(text = "Messages screen")
        Button(
            onClick = {
                // Navigates back to Home screen
                navigator.popBackStack()
            }
        ) {
            Text(text = "Navigate back")
        }
    }
}