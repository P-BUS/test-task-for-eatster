package com.obriysoft.test_task_for_eatster.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskfore.data.network.SlidesRemoteDataSourceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val slidesRemoteDataSourceImpl: SlidesRemoteDataSourceImpl
) : ViewModel() {

    init {
        viewModelScope.launch {
            val slides = slidesRemoteDataSourceImpl.getSlides()
            slides
        }
    }
}