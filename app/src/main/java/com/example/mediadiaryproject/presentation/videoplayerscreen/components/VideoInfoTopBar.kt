package com.example.mediadiaryproject.presentation.videoplayerscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediadiaryproject.R

@Composable
fun VideoInfoTopBar(
    modifier: Modifier = Modifier,
    closeMenu: () -> Unit,
    editMode: Boolean,
    turnOnEditMode: () -> Unit,
    saveInfo: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = Color.Transparent,
            )
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { closeMenu() },
            modifier = Modifier
                .padding(start = 20.dp)
                .size(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.close_icon),
                contentDescription = "close icon"
            )
        }
        IconButton(
            onClick = {
                if (editMode) {
                    saveInfo()
                } else {
                    turnOnEditMode()
                }
            },
            modifier = Modifier
                .padding(end = 20.dp)
                .size(30.dp)
        ) {
            if (editMode) {
                Icon(
                    painter = painterResource(id = R.drawable.save_icon),
                    contentDescription = "edit icon"
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.edit_icon),
                    contentDescription = "edit icon"
                )
            }

        }
    }
}

@Preview
@Composable
fun PhotoViewTopBarPreview() {
    VideoInfoTopBar(
        closeMenu = {},
        editMode = true,
        turnOnEditMode = {},
        saveInfo = {}
    )
}
