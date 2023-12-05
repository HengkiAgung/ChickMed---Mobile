package com.example.chickmed.ui.screen.analysis.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chickmed.data.model.ReportModel
import com.example.chickmed.data.model.SummaryModel
import com.example.chickmed.data.repository.ReportRepository
import com.example.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ReportViewModel(
    private val repository: ReportRepository
): ViewModel() {
    private val _reports: MutableStateFlow<UiState<List<ReportModel>>> = MutableStateFlow(UiState.Loading)
    val reports: StateFlow<UiState<List<ReportModel>>>
        get() = _reports

    private val _summary: MutableStateFlow<UiState<SummaryModel>> = MutableStateFlow(UiState.Loading)
    val summary: StateFlow<UiState<SummaryModel>>
        get() = _summary

    fun getReports() {
        viewModelScope.launch {
            repository.getReports("")
                .catch {
                    _reports.value = UiState.Error(it.message.toString())
                }
                .collect { reports ->
                    _reports.value = UiState.Success(reports)
                }
        }
    }

    fun getSummary() {
        viewModelScope.launch {
            repository.getSummary()
                .catch {
                    _summary.value = UiState.Error(it.message.toString())
                }
                .collect { summary ->
                    _summary.value = UiState.Success(summary)
                }
        }
    }

}