package com.example.mediadiaryproject.presentation.camerascreen.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState

//import java.io.File

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraContent(
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

        CameraTopBar(
            closeCamera = {},
            navigateToPhotos = {},
            navigateToVideos = {},
            modifier = Modifier.align(Alignment.TopCenter)
        )

        CameraBottomBar(
            videoMode = cameraScreenState.videoMode,
            lastCapturedPhoto = cameraScreenState.capturedImage,
            capturePhoto = { /*TODO*/ },
            changeMode = { value -> changeMode(value) },
            recordVideo = { /*TODO*/ },
            toggleCamera = { /*TODO*/ },
            displayLastPhoto = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }

}
