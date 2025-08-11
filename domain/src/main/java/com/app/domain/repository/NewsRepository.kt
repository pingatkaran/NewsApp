package com.app.domain.repository

import com.app.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(category: String): Flow<List<Article>>
    suspend fun refreshNews(category: String): Result<Unit>
}