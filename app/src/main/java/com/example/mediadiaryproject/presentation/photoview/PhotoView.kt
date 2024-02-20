package com.example.mediadiaryproject.presentation.photoview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.presentation.photoview.components.PhotoViewCore
import com.example.mediadiaryproject.presentation.photoview.viewmodel.PhotoViewViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PhotoView(
    navigator: DestinationsNavigator,
    viewModel: PhotoViewViewModel = hiltViewModel(),
    photoId: Int,
) {

    LaunchedEffect(true) {
        viewModel.getPhoto(photoId = photoId)
    }

    PhotoViewCore(
        photoState = viewModel.state.value,
        showMenu = viewModel.menuState.value,
        editMode = viewModel.editModeState.value,
        toggleMenu = { viewModel.toggleMenu() },
        closeView = { navigator.navigateUp() },
        updateTitle = { title -> viewModel.updateTitle(title) },
        updateDescription = { description -> viewModel.updateDescription(description) },
        turnOnEditMode = { viewModel.turnOnEditMode() },
        saveInfo = { viewModel.saveInfo() }
    )

}
