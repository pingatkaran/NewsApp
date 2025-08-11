package com.app.data.repository

import com.app.data.local.ArticleEntity
import com.app.data.remote.dto.ArticleDto
import com.app.domain.model.Article

fun ArticleDto.toEntity(category: String): ArticleEntity {
    return ArticleEntity(
        url = url,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceName = source.name,
        title = title,
        urlToImage = urlToImage,
        category = category
    )
}

fun ArticleEntity.toDomainArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceName = sourceName,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}