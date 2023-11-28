package com.example.chickmed.data.di

import android.content.Context
import com.example.chickmed.data.local.room.BookmarkArticleDatabase
import com.example.chickmed.data.repository.ArticleRepository

object Injection {
    fun provideRepository(context: Context): ArticleRepository {
        val favoriteDatabase = BookmarkArticleDatabase.getInstance(context)
        val favoriteDao = favoriteDatabase.bookmarkArticleDao()
        return ArticleRepository.getInstance(favoriteDao)
    }
}