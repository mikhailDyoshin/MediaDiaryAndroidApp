package com.example.mediadiaryproject.presentation.photosscreen.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.photosscreen.state.PhotoState

@Composable
fun PhotoItem(photo: PhotoState) {

    val image = photo.image

    Column {
        if (image != null) {
            Image(
                bitmap = image.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
        Text(photo.title)
        Text(photo.description)
    }
}

@Composable
@Preview
fun PhotoItemPreview() {

    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    PhotoItem(
        photo = PhotoState(
            title = "My photo",
            description = "This is a photo of my...",
            image = image
        )
    )
}