package com.example.chickmed.data.repository

import androidx.compose.runtime.mutableStateOf
import com.example.chickmed.data.local.preference.UserPreference
import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.model.ReportModel
import com.example.chickmed.data.model.SummaryModel
import com.example.chickmed.data.model.faker.FakeDataSource
import com.example.chickmed.data.remote.response.TemplateResponse
import com.example.chickmed.util.processError
import com.example.submission1.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ReportRepository (
    private val userPreference: UserPreference,
    private val apiService: ApiService
){
    private val dataReports = mutableListOf<ReportModel>()

    private val reports = mutableListOf<ReportModel>()
    private val summary = mutableStateOf<SummaryModel>(SummaryModel(0, 0, 0))

    fun getReports(page: Int): Flow<TemplateResponse<List<ReportModel>>> =
        flow {
            val token = userPreference.getUser().first().token
            val articlesFromApi = apiService.getReports(page = page, token = token)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(TemplateResponse(success = false, message = message, data = emptyList()))
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(this)
            }
        }.catch { e ->
            emit(TemplateResponse(success = false, message = e.message.toString(), data = emptyList()))
        }

    fun getSummary(): Flow<TemplateResponse<SummaryModel>> =
        flow {
            val token = userPreference.getUser().first().token
            val articlesFromApi = apiService.getSummary(token = token)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(TemplateResponse(success = false, message = message, data = SummaryModel(0, 0, 0)))
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(this)
            }
        }.catch { e ->
            emit(TemplateResponse(success = false, message = e.message.toString(), data = SummaryModel(0, 0, 0)))
        }

    fun getReportById(id: Int): Flow<TemplateResponse<ReportModel>> =
        flow {
            val token = userPreference.getUser().first().token
            val articlesFromApi = apiService.getReportById(id = id, token = token)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(TemplateResponse(success = false, message = message, data = ReportModel(0, "", "", "", emptyList())))
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(this)
            }
        }.catch { e ->
            emit(TemplateResponse(success = false, message = e.message.toString(), data = ReportModel(0, "", "", "", emptyList())))
        }

    companion object {
        @Volatile
        private var instance: ReportRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): ReportRepository =
            instance ?: synchronized(this) {
                instance ?: ReportRepository(userPreference, apiService)
            }.also { instance = it }
    }
}