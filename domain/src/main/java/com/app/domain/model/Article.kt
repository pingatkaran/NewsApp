package com.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String?
) : Parcelable