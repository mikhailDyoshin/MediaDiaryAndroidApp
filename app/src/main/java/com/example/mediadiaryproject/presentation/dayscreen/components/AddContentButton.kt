package com.example.mediadiaryproject.presentation.dayscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddContentButton(createContent: () -> Unit) {

    Box(
        modifier = Modifier
            .size(90.dp)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            .clickable(onClick = createContent)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Add-content-button's icon")
        }

    }

}

@Preview
@Composable
fun AddContentButtonPreview() {
    AddContentButton(createContent = {})
}
