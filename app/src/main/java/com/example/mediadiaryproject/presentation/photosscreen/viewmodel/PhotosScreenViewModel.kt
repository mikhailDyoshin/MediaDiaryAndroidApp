package com.example.mediadiaryproject.presentation.photosscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.GetListOfMediaUseCase
import com.example.mediadiaryproject.presentation.photosscreen.state.PhotoState
import com.example.mediadiaryproject.presentation.videoplayerscreen.state.VideoFileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosScreenViewModel @Inject constructor(
    private val getListOfAllVideosUseCase: GetListOfMediaUseCase,
) : ViewModel() {

    private val _state: MutableState<List<PhotoState>> = mutableStateOf(listOf())
    val state = _state

    init {
        getPhotosList()
    }

    private fun getPhotosList() {
        _state.value = getListOfAllVideosUseCase.execute(mediaType = MediaType.PHOTO).map { videoFileModel ->
            PhotoState(
                fileName = videoFileModel.fileName,
//                mediaItem = MediaItem.fromUri(videoFileModel.filePath.toUri())
            )
        }
    }

}