package com.example.mediadiaryproject.presentation.navgraph

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@NavGraph
annotation class MediaDiaryNavGraph(
    val start: Boolean = false
)
