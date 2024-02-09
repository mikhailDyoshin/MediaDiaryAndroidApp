package com.example.mediadiaryproject.presentation.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.mediadiaryproject.presentation.daycontentscreen.components.TextNoteCard
import com.example.mediadiaryproject.presentation.daycontentscreen.state.TextNoteCardState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun <T> Carousel(data: List<T>, cardContent: @Composable (data: T) -> Unit) {


    val pagerState = rememberPagerState(initialPage = (data.size / 2))

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.White)
            .padding(bottom = 5.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            IconButton(
                enabled = pagerState.currentPage > 0,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            ) {
                Icon(Icons.Default.KeyboardArrowLeft, null)
            }

            HorizontalPager(
                count = data.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 65.dp),
                modifier = Modifier
                    .height(110.dp)
                    .weight(1f)
            ) { page ->
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                val shadowElevation = if (page == pagerState.currentPage) 4.dp else 0.dp
                Column(
                    modifier = Modifier.shadow(
                        shadowElevation,
                        shape = RoundedCornerShape(10.dp),
                        clip = true
                    )
                ) {

                    Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
                        .graphicsLayer {
                            lerp(
                                start = 0.75f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }) {
                        cardContent(data[page])
                    }
                }
            }

            IconButton(
                enabled = pagerState.currentPage < pagerState.pageCount - 1,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            ) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                .height(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(data.size) {
                val color = if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .size(20.dp)
                        .background(color = color)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                )
            }
        }

    }
}

@Preview
@Composable
fun CarouselPreview() {

    val data = listOf(
        TextNoteCardState(
            id = 0,
            title = "Weather is about to change",
            text = "Weather is very good"
        ),
        TextNoteCardState(
            id = 1,
            title = "Weather is about to change",
            text = "Weather is very good"
        ),
        TextNoteCardState(
            id = 2,
            title = "Weather is about to change",
            text = "Weather is very good"
        ),
    )

    Carousel(data) { dataUnit ->
        TextNoteCard(state = dataUnit)
    }

}