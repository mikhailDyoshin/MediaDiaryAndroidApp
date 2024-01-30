package com.example.mediadiaryproject.presentation.camerascreen.components


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R

@Composable
fun CameraBottomBar(
    videoMode: Boolean,
    lastCapturedPhoto: Bitmap? = null,
    capturePhoto: () -> Unit,
    recordVideo: () -> Unit,
    toggleCamera: () -> Unit,
    displayLastPhoto: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { capturePhoto() }) {
                Text("Photo")
            }
            Button(onClick = { recordVideo() }) {
                Text("Video")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LastPhotoPreview(
                lastCapturedPhoto = lastCapturedPhoto,
                displayPhoto = { displayLastPhoto() }
            )
            CaptureButton(capture = { if (videoMode) recordVideo() else capturePhoto() })

            SwitchCameraButton(switchCamera = { toggleCamera() })
        }
    }

}

@Preview
@Composable
fun CameraBottomBarPreview() {

    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    CameraBottomBar(
        videoMode = true,
        lastCapturedPhoto = image,
        capturePhoto = {},
        recordVideo = {},
        toggleCamera = {},
        displayLastPhoto = {})
}
