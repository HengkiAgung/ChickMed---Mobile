package com.example.chickmed.data.repository

import com.example.chickmed.data.local.room.bookmark.BookmarkArticleDao
import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.model.faker.FakeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ArticleRepository (
    private val bookmarkArticleDao: BookmarkArticleDao
) {
    private val dataArticles = mutableListOf<ArticleModel>()
    private val articles = mutableListOf<ArticleModel>()

    init {
        if (dataArticles.isEmpty()) {
            FakeDataSource.dummyArticle.forEach {
                dataArticles.add(ArticleModel(it.id, it.title, it.content, it.image, it.date))
            }
        }
    }

    fun getArticles(query: String): Flow<List<ArticleModel>> {
        articles.clear()

        articles.addAll(dataArticles.filter {
            it.title.contains(query, true)
        })

        return flowOf(articles)
    }

    fun getArticleById(id: Int): Flow<ArticleModel> {
        return flowOf(articles.first {
            it.id == id
        })
    }

    fun isBookmarkArticle(id: Int) = bookmarkArticleDao.isBookmarkArticle(id)

    fun getBookmarkArticleModel(query: String) =  bookmarkArticleDao.getBookmarkArticle(query = query)

    suspend fun insertBookmarkArticle(id: Int) {
        dataArticles.map { article ->
            if (article.id == id) {
                bookmarkArticleDao.insertBookmarkArticle(article)
            }
        }
    }

    suspend fun deleteBookmarkArticle(id: Int) = bookmarkArticleDao.deleteBookmarkArticle(id)

    companion object {
        @Volatile
        private var instance: ArticleRepository? = null
        fun getInstance(
            bookmarkArticleDao: BookmarkArticleDao
        ): ArticleRepository =
            instance ?: synchronized(this) {
                instance ?: ArticleRepository(bookmarkArticleDao)
            }.also { instance = it }
    }
}