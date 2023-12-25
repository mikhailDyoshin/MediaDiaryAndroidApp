package com.example.mediadiaryproject.presentation.camerascreen.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import com.example.mediadiaryproject.presentation.camerascreen.state.CameraScreenState
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.SavePhotoToGalleryUseCase
import com.example.mediadiaryproject.domain.ProvideFileToSaveMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.Executor

@HiltViewModel
class CameraViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val savePhotoToGalleryUseCase: SavePhotoToGalleryUseCase,
    private val provideFileToSaveMediaUseCase: ProvideFileToSaveMediaUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(CameraScreenState())
    val state = _state

    private val _cameraController: MutableState<LifecycleCameraController> = mutableStateOf(
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }
    )

    val cameraController = _cameraController

    private var recording: Recording? = null

    init {
        _cameraController.value.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    }

    private fun storePhotoInInternalStorage(bitmap: Bitmap) {
//            savePhotoToGalleryUseCase.call(bitmap)
        val file = provideFileToSaveMediaUseCase.execute(mediaType = MediaType.PHOTO)

        try {
            // Create a FileOutputStream to write the bitmap data to the file
            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            }
            file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
        }
        updateCapturedPhotoState(bitmap)

    }

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.capturedImage?.recycle()
        _state.value = _state.value.copy(capturedImage = updatedPhoto)
    }

    fun toggleCamera() {
        if (_cameraController.value.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
            && _cameraController.value.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)
        ) {
            _cameraController.value.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
        } else if (_cameraController.value.cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA
            && _cameraController.value.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA)
        ) {
            _cameraController.value.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        }
    }

    @SuppressLint("MissingPermission")
    fun recordVideo(context: Context) {
        if (recording != null) {
            recording?.stop()
            recording = null


            val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM).toString())

//            logFilesInDir(directory)

            return
        }

        val file = provideFileToSaveMediaUseCase.execute(mediaType = MediaType.VIDEO)

        recording = _cameraController.value.startRecording(
            FileOutputOptions.Builder(file).build(),
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

    fun capturePhoto(
        context: Context,
    ) {
        val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

        _cameraController.value.takePicture(
            mainExecutor,
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    val correctedBitmap: Bitmap = image
                        .toBitmap()
                        .rotateBitmap(image.imageInfo.rotationDegrees)

                    storePhotoInInternalStorage(correctedBitmap)
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

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }

}