package com.example.mediadiaryproject.presentation.camerascreen.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
//import android.os.Environment
//import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.compose.foundation.layout.Row
//import java.io.File

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraContent(
    navigateToVideos: () -> Unit,
    toggleCamera: () -> Unit,
    recordVideo: (context: Context) -> Unit,
    capturePhoto: (context: Context) -> Unit,
    lastCapturedPhoto: Bitmap? = null,
    cameraController: LifecycleCameraController,
) {

    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row() {
                Button(onClick = { toggleCamera() }) {
                    Text("Toggle camera")
                }
                Button(onClick = { navigateToVideos() }) {
                    Text("Videos")
                }
            }

        },
        bottomBar = {
            Row() {
                Button(onClick = { capturePhoto(context) }) {
                    Text("Photo")
                }
                Button(onClick = { recordVideo(context) }) {
                    Text("Video")
                }
            }
        },
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







//private fun logFilesInDir(directory: File) {
//    // Get a list of files in the directory
//    val files: Array<out File>? = directory.listFiles()
//
//// Check if there are any files
//    if (files != null) {
//        // Iterate through the files and do something with each file
//
//        if (files.isEmpty()) {
//            Log.d("Recorded video file", "There are no files")
//        } else {
//            for (file in files) {
//                // Perform actions with each file
//                Log.d(
//                    "Recorded video file",
//                    "File Name: ${file.name}, Path: ${file.absolutePath}"
//                )
//            }
//        }
//
//    } else {
//        // The directory is empty or doesn't exist
//        Log.d("Recorded video file","No files found in the directory.")
//    }
//}
//
//private fun provideFileToSaveVideo(context: Context): File {
//
//    val nowTimestamp: Long = System.currentTimeMillis()
//
//    return File(
//        context.getExternalFilesDir(Environment.DIRECTORY_DCIM),
//        nowTimestamp.toString()
//    )
//}
