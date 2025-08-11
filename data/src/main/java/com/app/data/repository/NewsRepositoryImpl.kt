package com.app.data.repository

import com.app.data.local.ArticleDao
import com.app.data.remote.NewsApiService
import com.app.domain.model.Article
import com.app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
    private val articleDao: ArticleDao
) : NewsRepository {

    override fun getNews(category: String): Flow<List<Article>> {
        return articleDao.getArticlesByCategory(category.lowercase()).map { entities ->
            entities.map { it.toDomainArticle() }
        }
    }

    override suspend fun refreshNews(category: String): Result<Unit> {
        return try {
            val categoryQuery = if (category.equals("all", ignoreCase = true)) null else category
            val remoteArticles = apiService.getTopHeadlines(category = categoryQuery).articles

            val dbCategory = category.lowercase()

            articleDao.removeArticlesByCategory(dbCategory)
            articleDao.insertArticles(remoteArticles.map { it.toEntity(dbCategory) })
            
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}