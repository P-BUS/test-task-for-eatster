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
        startDestination = HOME_ROUTE
    ) {
        composable(HOME_ROUTE) {
            HomeScreen(
                homeViewModel = hiltViewModel<HomeViewModel>()
            )
        }
    }
}

private const val HOME_ROUTE = "slides_screen"