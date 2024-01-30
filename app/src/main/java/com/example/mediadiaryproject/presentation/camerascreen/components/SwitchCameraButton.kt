package com.example.mediadiaryproject.presentation.camerascreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R
import com.example.mediadiaryproject.ui.theme.HalfTransparent

@Composable
fun SwitchCameraButton(switchCamera: () -> Unit) {
    Column() {
        Box(
            modifier = Modifier
                .size(65.dp)
                .background(color = HalfTransparent, shape = CircleShape)
                .clickable(onClick = switchCamera)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.camera_rotate),
                    contentDescription = "Add-content-button's icon"
                )
            }
        }
    }


}

@Preview
@Composable
fun SwitchCameraButtonPreview() {
    SwitchCameraButton(switchCamera = {})
}