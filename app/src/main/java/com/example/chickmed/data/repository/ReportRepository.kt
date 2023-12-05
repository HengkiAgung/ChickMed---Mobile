package com.example.chickmed.data.repository

import androidx.compose.runtime.mutableStateOf
import com.example.chickmed.data.model.ReportModel
import com.example.chickmed.data.model.SummaryModel
import com.example.chickmed.data.model.faker.FakeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ReportRepository {
    private val dataReports = mutableListOf<ReportModel>()

    private val reports = mutableListOf<ReportModel>()
    private val summary = mutableStateOf<SummaryModel>(SummaryModel(0, 0, 0))

    init {
        if (dataReports.isEmpty()) {
            FakeDataSource.dummyReport.forEach {
                dataReports.add(it)
            }
        }
    }

    fun getReports(query: String): Flow<List<ReportModel>> {
        reports.clear()

        reports.addAll(dataReports.filter {
            it.date.contains(query, true)
        })

        return flowOf(reports)
    }

    fun getSummary(): Flow<SummaryModel> {
        return flowOf(summary.value)
    }

    fun getReportById(id: Int): ReportModel {
        return reports.first {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: ReportRepository? = null
        fun getInstance(): ReportRepository =
            instance ?: synchronized(this) {
                instance ?: ReportRepository()
            }.also { instance = it }
    }
}