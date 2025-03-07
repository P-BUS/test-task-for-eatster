package com.obriysoft.test_task_for_eatster.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskfore.data.repository.SlidesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val slidesRepository: SlidesRepository
) : ViewModel() {
    val slides = slidesRepository.slidesStream
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val _showPager: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showPager: StateFlow<Boolean> = _showPager.asStateFlow()

    fun updateSlides() {
        viewModelScope.launch {
            slidesRepository.updateSlidesDatabase()
        }
    }

    fun onTap() {
        viewModelScope.launch {
        }
    }

    fun startIdleTimer(time: Long = IDLE_TIME) {
        viewModelScope.launch {
            delay(time)
            updateShowPager(true)
        }
    }

    private fun updateShowPager(showPager: Boolean) {
        _showPager.update { showPager }
    }

    companion object {
        private const val IDLE_TIME = 20000L

    }
}