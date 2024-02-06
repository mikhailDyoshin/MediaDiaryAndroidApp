package com.example.mediadiaryproject.presentation.audiosplayscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.presentation.audiosplayscreen.state.AudioFileState
import com.example.mediadiaryproject.ui.theme.AudioItemBackGroundColor

@Composable
fun AudioItem(
    audio: AudioFileState,
    playAudio: (audio: AudioFileState) -> Unit,
    pauseAudio: () -> Unit,
    currentPosition: Float,
    seekTo: (position: Float) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = AudioItemBackGroundColor,
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                PlayPauseButton(
                    audio = audio,
                    play = { audio -> playAudio(audio) },
                    pause = { pauseAudio() })
                Text(
                    audio.fileName,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }


            if (audio.underFocus) {
                ProgressBar(currentPosition, seekTo = { position -> seekTo(position) })
            }


        }
    }


}

@Preview
@Composable
fun AudioItemPlayingPreview() {
    AudioItem(
        audio = AudioFileState(
            fileName = "My audio long long long long title",
            mediaItem = null,
            underFocus = true,
            isPlaying = true
        ),
        playAudio = {},
        pauseAudio = { /*TODO*/ },
        currentPosition = 0.4f,
        seekTo = {}
    )
}

@Preview
@Composable
fun AudioItemPlayingIdlePreview() {
    AudioItem(
        audio = AudioFileState(
            fileName = "My audio long long long long title",
            mediaItem = null,
            underFocus = true,
            isPlaying = false
        ),
        playAudio = {},
        pauseAudio = { /*TODO*/ },
        currentPosition = 0.4f,
        seekTo = {}
    )
}

@Preview
@Composable
fun AudioItemPlayingUnfocusedPreview() {
    AudioItem(
        audio = AudioFileState(
            fileName = "My audio long long long long title",
            mediaItem = null,
            underFocus = false,
            isPlaying = false
        ),
        playAudio = {},
        pauseAudio = { /*TODO*/ },
        currentPosition = 0.4f,
        seekTo = {}
    )
}