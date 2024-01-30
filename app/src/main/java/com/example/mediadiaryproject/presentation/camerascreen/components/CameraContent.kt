package com.example.mediadiaryproject.presentation.camerascreen.components

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState


@SuppressLint("RestrictedApi")
@Composable
fun CameraContent(
    closeCamera: () -> Unit,
    navigateToVideos: () -> Unit,
    navigateToPhotos: () -> Unit,
    toggleCamera: () -> Unit,
    changeMode: (videoModeOn: Boolean) -> Unit,
    recordVideo: (context: Context) -> Unit,
    capturePhoto: (context: Context) -> Unit,
    cameraScreenState: CameraScreenState,
    cameraController: LifecycleCameraController,
) {

    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier
                .padding(0.dp),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    setBackgroundColor(0)
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                }.also { previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }

        )

        if (!cameraScreenState.recording) {
            CameraTopBar(
                closeCamera = { closeCamera() },
                navigateToPhotos = { navigateToPhotos() },
                navigateToVideos = { navigateToVideos() },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }


        CameraBottomBar(
            cameraScreenState = cameraScreenState,
            capturePhoto = { capturePhoto(context) },
            changeMode = { value -> changeMode(value) },
            recordVideo = { recordVideo(context) },
            toggleCamera = { toggleCamera() },
            displayLastPhoto = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }

}
