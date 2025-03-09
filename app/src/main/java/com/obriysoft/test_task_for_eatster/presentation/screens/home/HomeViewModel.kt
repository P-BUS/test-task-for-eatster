package com.obriysoft.test_task_for_eatster.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskfore.data.repository.SlidesRepository
import com.obriysoft.test_task_for_eatster.domain.model.Slide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val slides: List<Slide> = emptyList(),
    val showPager: Boolean = false,
    val error: String? = null
)

sealed class HomeAction {
    data object OnScreenLoad : HomeAction()
    data object OnScreenTap : HomeAction()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val slidesRepository: SlidesRepository
) : ViewModel() {

    val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val sendAction: (HomeAction) -> Unit = { action ->
        when (action) {
            is HomeAction.OnScreenLoad -> {
                updateSlides()
                observeSlides()
                startIdleTimer()
            }

            is HomeAction.OnScreenTap -> onTap()
        }
    }

    private fun updateSlides() {
        viewModelScope.launch {
            slidesRepository.updateSlidesDatabase()
        }
    }

    private fun observeSlides() {
        viewModelScope.launch {
            slidesRepository.slidesStream
                .collectLatest { slides ->
                    _uiState.update { it.copy(slides = slides) }
                }
        }
    }

    private fun startIdleTimer(time: Long = IDLE_TIME) {
        viewModelScope.launch {
            delay(time)
            updateShowPager(true)
        }
    }

    private fun onTap() {
        viewModelScope.launch {
            updateShowPager(false)
            startIdleTimer()
        }
    }

    private fun updateShowPager(showPager: Boolean) {
        _uiState.update { it.copy(showPager = showPager) }
    }

    companion object {
        private const val IDLE_TIME = 3000L // TODO: to change back to 20000L
    }
}