package com.example.mediadiaryproject.presentation.warningwindow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.mediadiaryproject.ui.theme.HalfTransparent

@Composable
fun WarningWindow(
    message: String,
    onDiscard: () -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .zIndex(99f),
        color = HalfTransparent
    ) {
        Box {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                        .padding(start = 10.dp, end = 10.dp, top = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = message)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 10.dp, end = 10.dp),
                    ) {
                        Button(
                            onClick = { onCancel() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text(text = "Cancel")
                        }
                        Divider(
                            modifier = Modifier
                                .height(20.dp)
                                .width(2.dp)
                        )
                        Button(
                            onClick = { onDiscard() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text(text = "Discard")
                        }
                        Divider(
                            modifier = Modifier
                                .height(20.dp)
                                .width(2.dp)
                        )
                        Button(
                            onClick = { onSave() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            }

        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun WarningWindowPreview() {
    WarningWindow(
        message = "Some message here",
        onSave = {},
        onCancel = {},
        onDiscard = {}
    )
}