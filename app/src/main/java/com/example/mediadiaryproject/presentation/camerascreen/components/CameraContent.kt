package com.example.mediadiaryproject.presentation.camerascreen.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor
import androidx.camera.view.CameraController
import androidx.camera.view.video.AudioConfig
import androidx.compose.foundation.layout.Row
import java.io.File

private var recording: Recording? = null

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraContent(
    onPhotoCaptured: (Bitmap) -> Unit,
    lastCapturedPhoto: Bitmap? = null
) {

    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController =
        remember {
            LifecycleCameraController(context).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_CAPTURE or
                            CameraController.VIDEO_CAPTURE
                )
            }
        }

    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Button(onClick = { toggleCamera(cameraController = cameraController) }) {
                Text("Toggle camera")
            }
        },
        bottomBar = {
            Row() {
                Button(onClick = { capturePhoto(context, cameraController, onPhotoCaptured) }) {
                    Text("Photo")
                }
                Button(onClick = { recordVideo(controller = cameraController, context = context) }) {
                    Text("Video")
                }
            }
        },
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                text = { Text(text = "Take photo") },
//                onClick = { capturePhoto(context, cameraController, onPhotoCaptured) },
//                icon = {
//                    Icon(
//                        imageVector = Icons.Default.PlayArrow,
//                        contentDescription = "Camera capture icon"
//                    )
//                }
//            )
//        }
    ) { paddingValues: PaddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                factory = { context ->
                    PreviewView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                        )
                        setBackgroundColor(Color.BLACK)
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { previewView ->
                        previewView.controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                }
            )

            if (lastCapturedPhoto != null) {
                LastPhotoPreview(
                    modifier = Modifier.align(alignment = Alignment.BottomStart),
                    lastCapturedPhoto = lastCapturedPhoto
                )
            }
        }
    }
}

private fun capturePhoto(
    context: Context,
    cameraController: LifecycleCameraController,
    onPhotoCaptured: (Bitmap) -> Unit
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            val correctedBitmap: Bitmap = image
                .toBitmap()
                .rotateBitmap(image.imageInfo.rotationDegrees)

            onPhotoCaptured(correctedBitmap)
            image.close()
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
        }
    })
}

private fun Bitmap.rotateBitmap(rotationDegrees: Int): Bitmap {
    val matrix = Matrix().apply {
        postRotate(-rotationDegrees.toFloat())
        postScale(-1f, -1f)
    }

    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

private fun toggleCamera(cameraController: LifecycleCameraController) {
    if (cameraController.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
        && cameraController.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)
    ) {
        cameraController.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
    } else if (cameraController.cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA
        && cameraController.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA)
    ) {
        cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    }
}

@SuppressLint("MissingPermission")
private fun recordVideo(controller: LifecycleCameraController, context: Context) {
    if (recording != null) {
        recording?.stop()
        recording = null
        return
    }

//    if(!hasRequiredPermissions()) {
//        return
//    }

    val outputFile = File(
        context.filesDir,
            "my-recording.mp4"
        )

    recording = controller.startRecording(
        FileOutputOptions.Builder(outputFile).build(),
        AudioConfig.create(true),
        ContextCompat.getMainExecutor(context),
    ) { event ->
        when (event) {
            is VideoRecordEvent.Finalize -> {
                if (event.hasError()) {
                    recording?.close()
                    recording = null

                    Toast.makeText(
                        context,
                        "Video capture failed",
                        Toast.LENGTH_LONG
                    ).show()

                    Log.d("Video error", "${event.error}")
                } else {
                    Toast.makeText(
                        context,
                        "Video capture succeeded",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}

