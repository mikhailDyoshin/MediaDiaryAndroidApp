package com.example.mediadiaryproject.presentation.videoslistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.presentation.videoslistscreen.state.VideoListItemState

@Composable
fun VideosListScreenCore(videos: List<VideoListItemState>, playVideo: (videoId: Int) -> Unit) {

    Column {
        videos.forEach {video ->
            Row {
                if (video.videoFrame != null) {
                    Image(
                        bitmap = video.videoFrame.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }
                Text(video.videoFileName, modifier = Modifier.clickable { playVideo(video.videoId) })
            }

        }
    }

}
