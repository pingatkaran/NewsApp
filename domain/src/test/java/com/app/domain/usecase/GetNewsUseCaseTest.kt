package com.app.domain.usecase

import com.app.domain.model.Article
import com.app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetNewsUseCaseTest {

    private lateinit var useCase: GetNewsUseCase
    private lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetNewsUseCase(repository)
    }

    @Test
    fun `getNews returns flow from repository`() = runTest {
        val category = "technology"
        val articles = listOf(
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

        whenever(repository.getNews(category)).thenReturn(flowOf(articles))

        val result = useCase.getNews(category).first()

        assertEquals(articles, result)
        verify(repository).getNews(category)
    }

    @Test
    fun `refreshNews returns success result from repository`() = runTest {
        val category = "technology"
        val expectedResult = Result.success(Unit)

        whenever(repository.refreshNews(category)).thenReturn(expectedResult)

        val result = useCase.refreshNews(category)

        assertEquals(expectedResult, result)
        verify(repository).refreshNews(category)
    }

    @Test
    fun `refreshNews returns failure result from repository`() = runTest {
        val category = "technology"
        val exception = Exception("Network error")
        val expectedResult = Result.failure<Unit>(exception)

        whenever(repository.refreshNews(category)).thenReturn(expectedResult)

        val result = useCase.refreshNews(category)

        assertEquals(expectedResult, result)
        verify(repository).refreshNews(category)
    }
}