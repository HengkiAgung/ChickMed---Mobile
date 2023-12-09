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
    private val repository: ReportRepository
) : ViewModel() {
    private val _report: MutableStateFlow<UiState<ReportModel>> = MutableStateFlow(UiState.Loading)
    val report: StateFlow<UiState<ReportModel>>
        get() = _report

    fun getReportById(id: Int) {
        viewModelScope.launch {
            repository.getReportById(id)
                .catch {
                    _report.value = UiState.Error(it.message.toString())
                }
                .collect { report ->
                    _report.value = UiState.Success(report)
                }
        }
    }
}