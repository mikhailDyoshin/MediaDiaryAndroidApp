package com.example.mediadiaryproject.presentation.camerascreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.ui.theme.HalfTransparent

@Composable
fun CameraTopBar(
    closeCamera: () -> Unit,
    navigateToPhotos: () -> Unit,
    navigateToVideos: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = HalfTransparent,
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            )
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { closeCamera() },
            modifier = Modifier
                .padding(start = 20.dp)
                .size(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.close_icon),
                contentDescription = "photos"
            )
        }
        Row(
            modifier = Modifier
                .width(150.dp)
                .padding(end = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navigateToPhotos() }, modifier = Modifier.size(30.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.photos_icon),
                    contentDescription = "photos"
                )
            }
            IconButton(onClick = { navigateToVideos() }, modifier = Modifier.size(30.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.videos_icon),
                    contentDescription = "videos"
                )
            }
        }

    }
}

@Preview
@Composable
fun CameraTopBarPreview() {
    CameraTopBar(
        closeCamera = {},
        navigateToVideos = {},
        navigateToPhotos = {},
        modifier = Modifier
    )
}
