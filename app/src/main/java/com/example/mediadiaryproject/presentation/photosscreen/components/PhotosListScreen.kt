package com.example.mediadiaryproject.presentation.photosscreen.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.photosscreen.state.PhotoState

@Composable
fun PhotosListScreen(listOfPhotos: List<PhotoState>) {

    Column(modifier = Modifier
        .padding(horizontal = 10.dp)
        .fillMaxHeight()) {
        listOfPhotos.forEach { photo ->
            PhotoItem(photo = photo)
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PhotosListScreenPreview() {

    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    val listOfPhotos = listOf(
        PhotoState(
            title = "My photo 1",
            description = "This is a photo of my...",
            image = image
        ),
        PhotoState(
            title = "My photo long long long title",
            description = "This is a photo of my...",
            image = image
        ),
        PhotoState(
            title = "My photo 3",
            description = "This is a photo of my...",
            image = image
        )


    )

    PhotosListScreen(listOfPhotos = listOfPhotos)
}
