package com.app.data.repository

import com.app.data.local.ArticleEntity
import com.app.data.remote.dto.ArticleDto
import com.app.data.remote.dto.SourceDto
import com.app.domain.model.Article
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `ArticleDto toEntity maps correctly`() {
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

        val expectedEntity = ArticleEntity(
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

        val result = articleDto.toEntity(category)

        assertEquals(expectedEntity, result)
    }

    @Test
    fun `ArticleDto toEntity handles null values`() {
        val category = "sports"
        val articleDto = ArticleDto(
            author = null,
            content = null,
            description = null,
            publishedAt = "2024-01-01T12:00:00Z",
            source = SourceDto(id = null, name = "ESPN"),
            title = "Sports News",
            url = "https://espn.com/article",
            urlToImage = null
        )

        val expectedEntity = ArticleEntity(
            url = "https://espn.com/article",
            author = null,
            content = null,
            description = null,
            publishedAt = "2024-01-01T12:00:00Z",
            sourceName = "ESPN",
            title = "Sports News",
            urlToImage = null,
            category = category
        )

        val result = articleDto.toEntity(category)

        assertEquals(expectedEntity, result)
    }

    @Test
    fun `ArticleEntity toDomainArticle maps correctly`() {
        val articleEntity = ArticleEntity(
            url = "https://bbcnews.com/article",
            author = "Karan M",
            content = "I am god",
            description = "I am god",
            publishedAt = "2024-01-01T12:00:00Z",
            sourceName = "BBC",
            title = "Apple",
            urlToImage = "https://bbcnews.com/image.jpg",
            category = "technology"
        )

        val expectedArticle = Article(
            author = "Karan M",
            content = "I am god",
            description = "I am god",
            publishedAt = "2024-01-01T12:00:00Z",
            sourceName = "BBC",
            title = "Apple",
            url = "https://bbcnews.com/article",
            urlToImage = "https://bbcnews.com/image.jpg"
        )

        val result = articleEntity.toDomainArticle()

        assertEquals(expectedArticle, result)
    }

    @Test
    fun `ArticleEntity toDomainArticle handles null values`() {
        val articleEntity = ArticleEntity(
            url = "https://espn.com/article",
            author = null,
            content = null,
            description = null,
            publishedAt = "2024-01-01T12:00:00Z",
            sourceName = "ESPN",
            title = "Sports News",
            urlToImage = null,
            category = "sports"
        )

        val expectedArticle = Article(
            author = null,
            content = null,
            description = null,
            publishedAt = "2024-01-01T12:00:00Z",
            sourceName = "ESPN",
            title = "Sports News",
            url = "https://espn.com/article",
            urlToImage = null
        )

        val result = articleEntity.toDomainArticle()

        assertEquals(expectedArticle, result)
    }
}