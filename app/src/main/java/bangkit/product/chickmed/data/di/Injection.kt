package bangkit.product.chickmed.data.di

import android.content.Context
import bangkit.product.chickmed.data.local.preference.UserPreference
import bangkit.product.chickmed.data.local.preference.dataStore
import bangkit.product.chickmed.data.local.room.ScheduleDatabase
import bangkit.product.chickmed.data.remote.retrofit.ApiConfig
import bangkit.product.chickmed.data.repository.ArticleRepository
import bangkit.product.chickmed.data.repository.ReportRepository
import bangkit.product.chickmed.data.repository.ScheduleRepository
import bangkit.product.chickmed.data.repository.UserRepository

object Injection {
    fun provideArticleRepository(context: Context): ArticleRepository {
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return ArticleRepository.getInstance(apiService = apiService, userPreference = userPreference)
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
        val apiServiceMl = ApiConfig.getApiServiceMl()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return ReportRepository.getInstance(userPreference = userPreference, apiService = apiService, apiServiceMl = apiServiceMl)
    }
}