package com.example.chickmed.data.repository

import com.example.chickmed.data.local.room.bookmark.BookmarkArticleDao
import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.model.faker.FakeDataSource
import com.example.chickmed.data.remote.response.TemplateResponse
import com.example.submission1.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ArticleRepository (
    private val bookmarkArticleDao: BookmarkArticleDao,
    private val apiService: ApiService
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

    fun getArticles(page: Int): Flow<TemplateResponse<List<ArticleModel>>> =
        flow {
            val articlesFromApi = apiService.getArticles(page = page)
            articlesFromApi.body()?.let { emit(it) }
        }.catch { e ->
            emit(TemplateResponse(success = false, message = e.message.toString(), data = emptyList()))
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
            apiService: ApiService,
            bookmarkArticleDao: BookmarkArticleDao
        ): ArticleRepository =
            instance ?: synchronized(this) {
                instance ?: ArticleRepository(bookmarkArticleDao, apiService)
            }.also { instance = it }
    }
}