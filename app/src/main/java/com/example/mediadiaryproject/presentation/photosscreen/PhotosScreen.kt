package com.example.mediadiaryproject.presentation.photosscreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.photosscreen.viewmodel.PhotosScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PhotosScreen(
    navigator: DestinationsNavigator,
    viewModel: PhotosScreenViewModel = hiltViewModel(),
    dayId: Int,
) {

//    val context: Context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.getPhotosList(dayId = dayId)
    }

    val listOfPhotos = viewModel.state.value

    Column {
        Column {
            for (file in listOfPhotos) {
                val bitmap = file.image
                Column {
                    if (bitmap != null) {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                        )
                    }
                    Text(file.fileName)
                }

            }
        }

    }
}