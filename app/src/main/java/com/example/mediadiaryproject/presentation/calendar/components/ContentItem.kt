package com.example.mediadiaryproject.presentation.calendar.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediadiaryproject.presentation.calendar.state.CalendarState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(date: CalendarState.Date, onClickListener: (CalendarState.Date) -> Unit) {

    var buttonBackgroundColor = Color.White

    if (date.isSelected) {
        buttonBackgroundColor = Color.Gray
    }


    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .clickable { // making the element clickable, by adding 'clickable' modifier
                onClickListener(date)
            }
            .shadow(6.dp, shape = RoundedCornerShape(10.dp), clip = true),
        colors = CardDefaults.cardColors(
            containerColor = buttonBackgroundColor
        ),
    ) {
        Column(
            modifier = Modifier
                .height(54.dp)
                .width(45.dp)
                .padding(all = 8.dp)
        ) {
            Text(
                text = date.day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = date.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ContentItemPreview() {
    ContentItem(
        date = CalendarState.Date(
            date = LocalDate.now(),
            isSelected = false,
            isToday = true
        ), onClickListener = {})
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ContentItemSelectedPreview() {
    ContentItem(
        date = CalendarState.Date(
            date = LocalDate.now(),
            isSelected = true,
            isToday = true
        ), onClickListener = {})
}
