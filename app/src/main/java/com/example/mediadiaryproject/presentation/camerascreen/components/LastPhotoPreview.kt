package com.example.mediadiaryproject.presentation.camerascreen.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Picture
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.PictureDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R

@Composable
fun LastPhotoPreview(
    modifier: Modifier = Modifier,
    lastCapturedPhoto: Bitmap?,
    displayPhoto: () -> Unit,
) {

    val cornerSize = 65.dp

    val capturedPhoto: ImageBitmap? =
        remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto?.asImageBitmap() }

    Column(
        modifier = modifier
            .size(65.dp)
            .padding()
            .clickable { displayPhoto() }
            .background(color = Color.Gray, shape = RoundedCornerShape(size = cornerSize)),
    ) {
        if (capturedPhoto != null) {
            Image(
                bitmap = capturedPhoto,
                contentDescription = "Last captured photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(shape = RoundedCornerShape(size = cornerSize))
            )
        }

    }
}

@Preview
@Composable
fun LastPhotoPreviewEmptyPreview() {
    LastPhotoPreview(modifier = Modifier, lastCapturedPhoto = null, displayPhoto = {})
}

@Preview
@Composable
fun LastPhotoPreviewPreview() {

    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    LastPhotoPreview(modifier = Modifier, lastCapturedPhoto = image, displayPhoto = {})
}
