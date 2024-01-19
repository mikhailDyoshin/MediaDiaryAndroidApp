package com.example.mediadiaryproject.presentation.dayscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.presentation.dayscreen.DateState

@Composable
fun DayButton(date: DateState, navigateToDayContent: () -> Unit, modifier: Modifier) {

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                .clickable(onClick = { navigateToDayContent() })
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = date.month,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 65.sp
                )
                Text(
                    text = date.day,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 65.sp
                )
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun DayButtonPreview() {
    DayButton(date = DateState(day = "18", month = "Sep"), navigateToDayContent = {}, modifier = Modifier)
}
