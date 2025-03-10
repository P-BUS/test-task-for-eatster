package com.obriysoft.test_task_for_eatster.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.obriysoft.test_task_for_eatster.R
import com.obriysoft.test_task_for_eatster.domain.model.Slide
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    LaunchedEffect(Unit) {
        homeViewModel.sendAction(HomeAction.OnScreenLoad)
    }
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        slides = uiState.slides,
        showPager = uiState.showPager,
        onTap = { homeViewModel.sendAction(HomeAction.OnScreenTap) }
    )
}

@Composable
fun HomeScreenContent(
    slides: List<Slide>,
    showPager: Boolean,
    onTap: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { slides.size })

    if (showPager && slides.isNotEmpty()) {
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.settledPage }
                .filter { !pagerState.isScrollInProgress }
                .collectLatest { settledPage ->
                    delay(slides[settledPage].onScreenDuration.toLong())
                    pagerState.animateScrollToPage(
                        page = (settledPage + 1) % slides.size,
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
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
        ScreenSaver(
            modifier = Modifier
                .fillMaxSize(),
            slides = slides,
            showPager = showPager,
            pagerStateProvider = { pagerState },
            onTap = onTap
        )
    }
}

@Composable
fun ScreenSaver(
    modifier: Modifier = Modifier,
    slides: List<Slide>,
    showPager: Boolean,
    pagerStateProvider: () -> PagerState,
    onTap: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = showPager && slides.isNotEmpty(),
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 2000,
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 1000,
            )
        ),
    ) {
        HorizontalPager(
            state = pagerStateProvider(),
            modifier = Modifier
                .fillMaxSize()
                .clickable { onTap() }
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(slides[page].imageUrl)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}