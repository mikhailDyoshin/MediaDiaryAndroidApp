package com.example.mediadiaryproject.presentation.videoslistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mediadiaryproject.presentation.videoplayerscreen.state.VideoFileState
import com.example.mediadiaryproject.presentation.videoslistscreen.state.VideoListItemState

@Composable
fun VideosListScreenCore(videos: List<VideoListItemState>, playVideo: (videoId: Int) -> Unit) {

    Column {
        videos.forEach {video ->
            Text(video.videoFileName, modifier = Modifier.clickable { playVideo(video.videoId) })
        }
    }

}

