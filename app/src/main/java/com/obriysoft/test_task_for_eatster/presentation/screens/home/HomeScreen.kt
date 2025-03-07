package com.obriysoft.test_task_for_eatster.presentation.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.obriysoft.test_task_for_eatster.domain.model.Slide

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    LaunchedEffect(Unit) {
        homeViewModel.updateSlides()
    }
    val slides by homeViewModel.slides.collectAsStateWithLifecycle()

    HomeScreenContent(slides)
}

@Composable
fun HomeScreenContent(slides: List<Slide>) {
    Text(
        text = slides.toString(),
        color = Color.Red
    )
}