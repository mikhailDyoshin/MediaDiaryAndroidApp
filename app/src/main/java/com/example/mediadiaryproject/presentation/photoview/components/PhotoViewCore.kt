package com.example.mediadiaryproject.presentation.photoview.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
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
fun PhotoViewCore(photoState: PhotoViewState, showMenu: Boolean) {
    Box() {

        Image(
            bitmap = photoState.image.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        if (showMenu) {
            PhotoViewInfo(
                title = photoState.title,
                description = photoState.description,
                modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            )
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
        photoState = PhotoViewState(title = "", description = "", image = image),
        showMenu = false
    )
}

@Preview(showSystemUi = true)
@Composable
fun PhotoViewCoreWithMenuPreview() {
    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    PhotoViewCore(
        photoState = PhotoViewState(
            title = "My sad cat",
            description = "It's a lonely sad cat on a cold street",
            image = image
        ),
        showMenu = true
    )
}