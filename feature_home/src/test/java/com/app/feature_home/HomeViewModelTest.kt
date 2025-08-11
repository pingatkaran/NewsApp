package com.app.feature_home

import com.app.domain.model.Article
import com.app.domain.usecase.GetNewsUseCase
import com.app.feature_home.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: HomeViewModel

    private lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNewsUseCase = mock()

        runBlocking {
            whenever(getNewsUseCase.refreshNews("all")).thenReturn(Result.success(Unit))
        }
        whenever(getNewsUseCase.getNews("all")).thenReturn(flowOf(emptyList()))
        viewModel = HomeViewModel(getNewsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onCategorySelected updates state and fetches news`() = runTest {
        val category = "Technology"
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

        runBlocking {
            whenever(getNewsUseCase.refreshNews(category.lowercase())).thenReturn(
                Result.success(
                    Unit
                )
            )
        }
        whenever(getNewsUseCase.getNews(category.lowercase())).thenReturn(flowOf(articles))

        viewModel.onCategorySelected(category)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(category, uiState.selectedCategory)
        assertEquals(false, uiState.isLoading)
        assertEquals(null, uiState.error)
        assertEquals(articles, uiState.articles)
    }
}