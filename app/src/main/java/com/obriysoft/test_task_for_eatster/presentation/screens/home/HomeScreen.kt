package com.obriysoft.test_task_for_eatster.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.obriysoft.test_task_for_eatster.domain.model.Slide
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    LaunchedEffect(Unit) {
        homeViewModel.updateSlides()
        homeViewModel.startIdleTimer()
    }
    val slides by homeViewModel.slides.collectAsStateWithLifecycle()
    val showPager by homeViewModel.showPager.collectAsStateWithLifecycle()

    if (slides.isNotEmpty()) {
        HomeScreenContent(
            slides = slides,
            showPager = showPager,
            onTap = homeViewModel::onTap,
        )
    } else {
        LoadingScreen()
    }
}

@Composable
fun HomeScreenContent(
    slides: List<Slide>,
    showPager: Boolean,
    onTap: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var showPager by remember { mutableStateOf(showPager) }
    val pagerState = rememberPagerState(pageCount = { slides.size })

    LaunchedEffect(pagerState.currentPage) {
        delay(slides[pagerState.currentPage].onScreenDuration.toLong())
        scope.launch {
            pagerState.animateScrollToPage(
                (pagerState.currentPage + 1) % slides.size
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello Hello Hello",
            color = Color.White
        )
    }

    AnimatedVisibility(
        visible = showPager,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onTap()
                    showPager = false
                }
        ) { page ->
            // TODO: to replace with Coil with cashing
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(slides[page].imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier,
            color = Color.White,
            strokeWidth = 4.dp,
        )
    }
}