package com.app.feature_home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    val categories = listOf("All", "Business", "Entertainment", "Health", "Science", "Sports", "Technology")

    init {
        onCategorySelected(categories.first())
    }

    fun onCategorySelected(category: String) {
        if (category == _uiState.value.selectedCategory && _uiState.value.articles.isNotEmpty()) {
            return
        }

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(selectedCategory = category, isLoading = true, error = null) }

            val result = getNewsUseCase.refreshNews(category.lowercase())

            if (result.isFailure) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Failed to refresh news.")
                }
                return@launch // Stop the process here.
            }

            getNewsUseCase.getNews(category.lowercase())
                .collect { articles ->
                    _uiState.update {
                        it.copy(
                            articles = articles,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }
}