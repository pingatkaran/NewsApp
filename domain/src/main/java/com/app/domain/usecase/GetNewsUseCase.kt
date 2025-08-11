package com.app.domain.usecase

import com.app.domain.model.Article
import com.app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    fun getNews(category: String): Flow<List<Article>> {
        return repository.getNews(category)
    }

    suspend fun refreshNews(category: String): Result<Unit> {
        return repository.refreshNews(category)
    }
}