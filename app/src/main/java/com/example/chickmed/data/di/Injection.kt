package com.example.chickmed.data.di

import android.content.Context
import com.example.chickmed.data.local.preference.UserPreference
import com.example.chickmed.data.local.preference.dataStore
import com.example.chickmed.data.local.room.bookmark.BookmarkArticleDatabase
import com.example.chickmed.data.local.room.schedule.ScheduleDatabase
import com.example.chickmed.data.repository.ArticleRepository
import com.example.chickmed.data.repository.ScheduleRepository
import com.example.chickmed.data.repository.UserRepository

object Injection {
    fun provideArticleRepository(context: Context): ArticleRepository {
        val favoriteDatabase = BookmarkArticleDatabase.getInstance(context)
        val favoriteDao = favoriteDatabase.bookmarkArticleDao()
        return ArticleRepository.getInstance(favoriteDao)
    }

    fun provideUserRepository(context: Context): UserRepository {
        val userPreference = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(userPreference)
    }

    fun provideScheduleRepository(context: Context): ScheduleRepository {
        val scheduleDatabase = ScheduleDatabase.getInstance(context)
        val scheduleDao = scheduleDatabase.scheduleDao()
        return ScheduleRepository.getInstance(scheduleDao)
    }
}