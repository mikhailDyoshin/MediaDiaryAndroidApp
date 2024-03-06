package com.example.mediadiaryproject.presentation.photoview

import android.util.Log
import androidx.activity.compose.BackHandler
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

    val editModeOn = viewModel.editModeState.value

    BackHandler(enabled = true, onBack = {
        navigateUpLogic(navigator = navigator, editModeState = editModeOn) {
            viewModel.displayWarningWindow()
        }
    })

    PhotoViewCore(
        photoState = viewModel.state.value,
        warningWindowDisplayed = viewModel.warningWindowState.value,
        showMenu = viewModel.menuState.value,
        editMode = editModeOn,
        toggleMenu = { viewModel.toggleMenu() },
        closeView = {
            navigateUpLogic(navigator = navigator, editModeState = editModeOn) {
                viewModel.displayWarningWindow()
            }
        },
        updateTitle = { title -> viewModel.updateTitle(title) },
        updateDescription = { description -> viewModel.updateDescription(description) },
        turnOnEditMode = { viewModel.turnOnEditMode() },
        saveInfo = { viewModel.saveInfo() },
        onCancel = { viewModel.closeWarningWindow() },
        onDiscard = {
            viewModel.closeWarningWindow()
            navigator.navigateUp()
        },
        onSave = {
            viewModel.saveInfo()
            viewModel.closeWarningWindow()
            navigator.navigateUp()
        }
    )

}

private fun navigateUpLogic(
    navigator: DestinationsNavigator,
    editModeState: Boolean,
    callback: () -> Unit
) {
    if (editModeState) {
        callback()
        Log.d("Warning window", "Warning window is displayed")
    } else {
        navigator.navigateUp()
    }
}
