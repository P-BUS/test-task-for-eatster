package com.obriysoft.test_task_for_eatster.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.obriysoft.test_task_for_eatster.presentation.screens.home.HomeScreen
import com.obriysoft.test_task_for_eatster.presentation.screens.home.HomeViewModel

@Composable
fun SlidesNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "slidesscreen"
    ) {
        composable("slidesscreen") {
            HomeScreen(
                homeViewModel = hiltViewModel<HomeViewModel>()
            )
        }
    }
}