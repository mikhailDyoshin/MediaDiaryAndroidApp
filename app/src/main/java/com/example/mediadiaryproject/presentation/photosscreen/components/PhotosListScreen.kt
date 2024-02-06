package com.example.mediadiaryproject.presentation.photosscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.mediadiaryproject.presentation.photosscreen.state.PhotoState

@Composable
fun PhotosListScreen(listOfPhotos: List<PhotoState>) {

    Column {
        Column {
            listOfPhotos.forEach { photo ->
                PhotoItem(photo = photo)
            }
        }

    }
}