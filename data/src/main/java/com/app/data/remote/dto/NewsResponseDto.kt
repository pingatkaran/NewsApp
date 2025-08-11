package com.app.data.remote.dto

data class NewsResponseDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String?
)

data class SourceDto(
    val id: String?,
    val name: String
)