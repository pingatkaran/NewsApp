package com.app.feature_home.home

import com.app.domain.model.Article

data class HomeUiState(
    val articles: List<Article> = emptyList(),
    val selectedCategory: String = "All",
    val isLoading: Boolean = false,
    val error: String? = null
)
