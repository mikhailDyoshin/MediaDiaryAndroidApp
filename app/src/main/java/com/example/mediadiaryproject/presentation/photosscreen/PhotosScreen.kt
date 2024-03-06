package com.example.mediadiaryproject.presentation.photosscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.destinations.PhotoViewDestination
import com.example.mediadiaryproject.presentation.photosscreen.components.PhotosListScreen
import com.example.mediadiaryproject.presentation.photosscreen.viewmodel.PhotosScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PhotosScreen(
    navigator: DestinationsNavigator,
    viewModel: PhotosScreenViewModel = hiltViewModel(),
    dayId: Int,
) {

//    val context: Context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.getPhotosList(dayId = dayId)
    }

    val listOfPhotos = viewModel.state.value

    PhotosListScreen(
        listOfPhotos,
        openPhoto = { id -> navigator.navigate(PhotoViewDestination(photoId = id)) })
}