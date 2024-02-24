package com.example.mediadiaryproject.presentation.videoplayerscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.mediadiaryproject.R

@Composable
fun VideoPlayerTopBar(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    showInfo: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(99f),
        color = Color.Transparent
    ) {
        Box {
            Row(
                modifier = modifier
                    .background(
                        color = Color.Transparent,
                    )
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .align(Alignment.TopCenter),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { navigateBack() },
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "back icon",
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {
                        showInfo()
                    },
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.description_icon),
                        contentDescription = "info icon",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun VideoPlayerTopBarPreview() {
    VideoPlayerTopBar(
        navigateBack = {},
        showInfo = {},
    )
}
