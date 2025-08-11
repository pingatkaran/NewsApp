package com.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles WHERE category = :category")
    fun getArticlesByCategory(category: String): Flow<List<ArticleEntity>>

    @Query("DELETE FROM articles WHERE category = :category")
    suspend fun removeArticlesByCategory(category: String)
}