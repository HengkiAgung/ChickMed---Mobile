package com.example.chickmed.data.di

import android.content.Context
import com.example.chickmed.data.local.preference.UserPreference
import com.example.chickmed.data.local.preference.dataStore
import com.example.chickmed.data.local.room.bookmark.BookmarkArticleDatabase
import com.example.chickmed.data.local.room.schedule.ScheduleDatabase
import com.example.chickmed.data.remote.retrofit.ApiConfig
import com.example.chickmed.data.repository.ArticleRepository
import com.example.chickmed.data.repository.ReportRepository
import com.example.chickmed.data.repository.ScheduleRepository
import com.example.chickmed.data.repository.UserRepository

object Injection {
    fun provideArticleRepository(context: Context): ArticleRepository {
        val apiService = ApiConfig.getApiService()
        val favoriteDatabase = BookmarkArticleDatabase.getInstance(context)
        val favoriteDao = favoriteDatabase.bookmarkArticleDao()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return ArticleRepository.getInstance(bookmarkArticleDao = favoriteDao, apiService = apiService, userPreference = userPreference)
    }

    fun provideUserRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(userPreference = userPreference, apiService = apiService)
    }

    fun provideScheduleRepository(context: Context): ScheduleRepository {
        val scheduleDatabase = ScheduleDatabase.getInstance(context)
        val scheduleDao = scheduleDatabase.scheduleDao()
        return ScheduleRepository.getInstance(scheduleDao)
    }

    fun provideReportRepository(context: Context): ReportRepository {
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return ReportRepository.getInstance(userPreference = userPreference, apiService = apiService)
    }
}