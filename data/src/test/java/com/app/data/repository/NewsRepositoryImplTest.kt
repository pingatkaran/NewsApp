package com.app.data.repository

import com.app.data.local.ArticleDao
import com.app.data.local.ArticleEntity
import com.app.data.remote.NewsApiService
import com.app.data.remote.dto.ArticleDto
import com.app.data.remote.dto.NewsResponseDto
import com.app.data.remote.dto.SourceDto
import com.app.domain.model.Article
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import java.io.IOException

class NewsRepositoryImplTest {

    private lateinit var repository: NewsRepositoryImpl
    private lateinit var apiService: NewsApiService
    private lateinit var articleDao: ArticleDao

    @Before
    fun setUp() {
        apiService = mock()
        articleDao = mock()
        repository = NewsRepositoryImpl(apiService, articleDao)
    }

    @Test
    fun `getNews returns flow from dao mapped to domain articles`() = runTest {
        val category = "technology"
        val entities = listOf(
            ArticleEntity(
                url = "https://bbcnews.com/article",
                author = "Karan M",
                content = "I am god",
                description = "I am god",
                publishedAt = "2024-01-01T12:00:00Z",
                sourceName = "BBC",
                title = "Apple",
                urlToImage = "https://bbcnews.com/image.jpg",
                category = category
            )
        )

        val expectedArticles = listOf(
            Article(
                author = "Karan M",
                content = "I am god",
                description = "I am god",
                publishedAt = "2024-01-01T12:00:00Z",
                sourceName = "BBC",
                title = "Apple",
                url = "https://bbcnews.com/article",
                urlToImage = "https://bbcnews.com/image.jpg"
            )
        )

        whenever(articleDao.getArticlesByCategory(category)).thenReturn(flowOf(entities))

        val result = repository.getNews(category).first()

        Assert.assertEquals(expectedArticles, result)
        verify(articleDao).getArticlesByCategory(category)
    }

    @Test
    fun `refreshNews success - fetches and saves articles to database`() = runTest {
        val category = "technology"
        val articleDto = ArticleDto(
            author = "Karan M",
            content = "I am god",
            description = "I am god",
            publishedAt = "2024-01-01T12:00:00Z",
            source = SourceDto(id = "bbc", name = "BBC"),
            title = "Apple",
            url = "https://bbcnews.com/article",
            urlToImage = "https://bbcnews.com/image.jpg"
        )
        val newsResponse = NewsResponseDto(
            articles = listOf(articleDto),
            status = "ok",
            totalResults = 1
        )

        whenever(apiService.getTopHeadlines(category = category)).thenReturn(newsResponse)

        val result = repository.refreshNews(category)

        Assert.assertTrue(result.isSuccess)
        verify(apiService).getTopHeadlines(category = category)
        verify(articleDao).removeArticlesByCategory(category)
        verify(articleDao).insertArticles(
            listOf(
                ArticleEntity(
                    url = "https://bbcnews.com/article",
                    author = "Karan M",
                    content = "I am god",
                    description = "I am god",
                    publishedAt = "2024-01-01T12:00:00Z",
                    sourceName = "BBC",
                    title = "Apple",
                    urlToImage = "https://bbcnews.com/image.jpg",
                    category = category
                )
            )
        )
    }

    @Test
    fun `refreshNews with all category uses null for api call`() = runTest {
        val category = "all"
        val newsResponse = NewsResponseDto(
            articles = emptyList(),
            status = "ok",
            totalResults = 0
        )

        whenever(apiService.getTopHeadlines(category = null)).thenReturn(newsResponse)

        val result = repository.refreshNews(category)

        Assert.assertTrue(result.isSuccess)
        verify(apiService).getTopHeadlines(category = null)
        verify(articleDao).removeArticlesByCategory("all")
    }

    @Test
    fun `refreshNews returns failure on HttpException`() = runTest {
        val category = "technology"
        val exception = HttpException(mock())

        whenever(apiService.getTopHeadlines(category = category)).thenThrow(exception)

        val result = repository.refreshNews(category)

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `refreshNews returns failure on network exception`() = runTest {
        val category = "technology"
        val exception = RuntimeException("Network error")

        whenever(apiService.getTopHeadlines(category = category)).thenThrow(exception)

        val result = repository.refreshNews(category)

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }
}