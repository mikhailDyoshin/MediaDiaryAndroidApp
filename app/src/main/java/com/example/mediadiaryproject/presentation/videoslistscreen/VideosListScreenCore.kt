package com.example.mediadiaryproject.presentation.videoslistscreen

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.presentation.videoslistscreen.state.VideoFileState
import com.example.mediadiaryproject.presentation.videoslistscreen.components.VideoItem

@Composable
fun VideosListScreenCore(videos: List<VideoFileState>, playVideo: (videoId: Int) -> Unit) {

    Column {
        videos.forEach {video ->
            VideoItem(video = video, playVideo = { id -> playVideo(id) })
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun VideosListScreenPreview() {

    val context = LocalContext.current

    val imageId = R.drawable.placeholder_image
    val image = BitmapFactory.decodeResource(context.resources, imageId)

    VideosListScreenCore(
        videos = listOf(
            VideoFileState(
                videoId = 0,
                title = "Video 1",
                description = "This video is about...",
                preview = image
            ),
            VideoFileState(
                videoId = 1,
                title = "Video 2",
                description = "This video is about...",
                preview = image
            ),
            VideoFileState(
                videoId = 2,
                title = "Video 3",
                description = "This video is about...",
                preview = image
            )
        ), playVideo = {})
}