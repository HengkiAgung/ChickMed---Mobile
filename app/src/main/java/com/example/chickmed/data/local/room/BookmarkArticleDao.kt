package com.example.chickmed.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chickmed.data.model.ArticleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkArticleDao {
    @Query("SELECT * FROM bookmark_article WHERE title LIKE '%' || :query || '%'")
    fun getBookmarkArticle(query: String): Flow<List<ArticleModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmarkArticle(bookmarkArticle: ArticleModel)

    @Query("DELETE FROM bookmark_article WHERE id = :id")
    suspend fun deleteBookmarkArticle(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM bookmark_article WHERE id = :id)")
    fun isBookmarkArticle(id: Int): Flow<Boolean>
}