package com.example.chickmed.ui.screen.analysis.detail_analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chickmed.data.model.ReportModel
import com.example.chickmed.data.repository.ReportRepository
import com.example.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailAnalysisViewModel(
    private val reportRepository: ReportRepository
) : ViewModel() {
    private val _report: MutableStateFlow<UiState<ReportModel>> = MutableStateFlow(UiState.Loading)
    val report: StateFlow<UiState<ReportModel>>
        get() = _report

    fun getReportById(id: Int) {
        viewModelScope.launch {
            reportRepository.getReportById(id = id)
                .catch {
                    _report.value = UiState.Error(it.message.toString())
                }
                .collect { articles ->
                    try {
                        if (!articles.success) {
                            if (articles.message == "Unauthorized") {
                                _report.value = UiState.Unauthorized
                                return@collect
                            }
                            _report.value = UiState.Error(articles.message)
                            return@collect
                        }
                        _report.value = UiState.Success(articles.data)
                    } catch (e: Exception) {
                        _report.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }
}