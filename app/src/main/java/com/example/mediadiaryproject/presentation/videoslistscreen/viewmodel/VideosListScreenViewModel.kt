package com.example.mediadiaryproject.presentation.videoslistscreen.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediadiaryproject.common.MediaType
import com.example.mediadiaryproject.domain.usecase.GetListOfMediaByDayAndTypeUseCase
import com.example.mediadiaryproject.presentation.videoslistscreen.state.VideoFileState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class VideosListScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getListOfAllVideosUseCase: GetListOfMediaByDayAndTypeUseCase,
) : ViewModel() {

    private val _state: MutableState<List<VideoFileState>> = mutableStateOf(listOf())
    val state = _state

    private val retriever = MediaMetadataRetriever()

    fun getVideosList(dayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value =
                getListOfAllVideosUseCase.execute(mediaType = MediaType.VIDEO, dayId = dayId)
                    .map { videoFileModel ->
                        VideoFileState(
                            videoId = videoFileModel.id,
                            title = videoFileModel.title,
                            description = videoFileModel.description,
                            preview = retrieveFirstFrame(uri = videoFileModel.pathToFile.toUri())
                        )
                    }
        }

    }

    private fun retrieveFirstFrame(uri: Uri): Bitmap? {
        retriever.setDataSource(context, uri)
        return retriever.getFrameAtTime(0)
    }

}