package com.example.mediadiaryproject.presentation.photoview.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.photoview.state.PhotoViewState

@Composable
fun PhotoViewCore(
    photoState: PhotoViewState?,
    warningWindowDisplayed: Boolean,
    showMenu: Boolean,
    editMode: Boolean,
    toggleMenu: () -> Unit,
    closeView: () -> Unit,
    turnOnEditMode: () -> Unit,
    saveInfo: () -> Unit,
    updateTitle: (title: String) -> Unit,
    updateDescription: (description: String) -> Unit,
    onCancel: () -> Unit,
    onDiscard: () -> Unit,
    onSave: () -> Unit,
) {
    Box() {

        if (warningWindowDisplayed) {
            PhotoViewWarningWindow(
                onDiscard = { onDiscard() },
                onSave = { onSave() },
                onCancel = { onCancel() },
                modifier = Modifier.align(Alignment.Center)
            )
        }


        if (photoState != null) {
            if (photoState.image != null) {
                Image(
                    bitmap = photoState.image.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { toggleMenu() }
                )
            }
            if (showMenu) {
                PhotoViewTopBar(
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    ),
                    closeView = { closeView() },
                    editMode = editMode,
                    turnOnEditMode = { turnOnEditMode() },
                    saveInfo = { saveInfo() }
                )

                PhotoViewInfo(
                    editMode = editMode,
                    title = photoState.title,
                    description = photoState.description,
                    updateTitle = { title -> updateTitle(title) },
                    updateDescription = { description -> updateDescription(description) },
                    modifier = Modifier.align(
                        Alignment.BottomCenter
                    )
                )
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PhotoViewCorePreview() {
    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    PhotoViewCore(
        editMode = false,
        warningWindowDisplayed = false,
        photoState = PhotoViewState(title = "", description = "", image = image),
        showMenu = false,
        toggleMenu = {},
        closeView = {},
        updateTitle = {},
        updateDescription = {},
        turnOnEditMode = {},
        saveInfo = {},
        onCancel = {},
        onDiscard = {},
        onSave = {},
    )
}

@Preview(showSystemUi = true)
@Composable
fun PhotoViewCoreWithMenuPreview() {
    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    PhotoViewCore(
        editMode = false,
        warningWindowDisplayed = false,
        photoState = PhotoViewState(
            title = "My sad cat",
            description = "It's a lonely sad cat on a cold street",
            image = image
        ),
        showMenu = true,
        toggleMenu = {},
        closeView = {},
        updateTitle = {},
        updateDescription = {},
        turnOnEditMode = {},
        saveInfo = {},
        onCancel = {},
        onDiscard = {},
        onSave = {},
    )
}

@Preview(showSystemUi = true)
@Composable
fun PhotoViewCoreWithMenuEditModePreview() {
    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    PhotoViewCore(
        editMode = true,
        warningWindowDisplayed = false,
        photoState = PhotoViewState(
            title = "My sad cat",
            description = "It's a lonely sad cat on a cold street",
            image = image
        ),
        showMenu = true,
        toggleMenu = {},
        closeView = {},
        updateTitle = {},
        updateDescription = {},
        turnOnEditMode = {},
        saveInfo = {},
        onCancel = {},
        onDiscard = {},
        onSave = {},
    )
}

@Preview(showSystemUi = true)
@Composable
fun PhotoViewCoreWithWarningWindowPreview() {
    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    PhotoViewCore(
        editMode = true,
        warningWindowDisplayed = true,
        photoState = PhotoViewState(
            title = "My sad cat",
            description = "It's a lonely sad cat on a cold street",
            image = image
        ),
        showMenu = true,
        toggleMenu = {},
        closeView = {},
        updateTitle = {},
        updateDescription = {},
        turnOnEditMode = {},
        saveInfo = {},
        onCancel = {},
        onDiscard = {},
        onSave = {},
    )
}
