package com.example.mediadiaryproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mediadiaryproject.presentation.NavGraphs
import com.example.mediadiaryproject.presentation.camerascreen.viewmodel.CameraViewModel
import com.example.mediadiaryproject.presentation.mainscreen.MainScreen
import com.example.mediadiaryproject.ui.theme.MediaDiaryProjectTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaDiaryProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)

                    val cameraViewModel: CameraViewModel by viewModels()

                    val cameraState = cameraViewModel.state

//                    MainScreen(
//                        cameraState = cameraState.value,
//                        onPhotoCaptured = { bitmap ->
//                            cameraViewModel.storePhotoInGallery(bitmap = bitmap)
//                        }
//                    )
                }
            }
        }
    }
}
