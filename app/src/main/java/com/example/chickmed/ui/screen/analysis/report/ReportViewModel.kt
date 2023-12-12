package com.example.chickmed.ui.screen.analysis.report

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    private val reportRepository: ReportRepository
): ViewModel() {
    private val _reports: MutableStateFlow<UiState<List<ReportModel>>> = MutableStateFlow(UiState.Loading)
    val reports: StateFlow<UiState<List<ReportModel>>>
        get() = _reports

    private val _summary: MutableState<UiState<SummaryModel>> = mutableStateOf(UiState.Loading)
    val summary: MutableState<UiState<SummaryModel>>
        get() = _summary

    fun getReports(page: Int) {
        viewModelScope.launch {
            if (_reports.value is UiState.Success) {
                return@launch
            }
            reportRepository.getReports(page = page)
                .catch {
                    _reports.value = UiState.Error(it.message.toString())
                }
                .collect { articles ->
                    try {
                        if (!articles.success) {
                            if (articles.message == "Unauthorized") {
                                _reports.value = UiState.Unauthorized
                                return@collect
                            }
                            _reports.value = UiState.Error(articles.message)
                            return@collect
                        }
                        _reports.value = UiState.Success(articles.data)
                    } catch (e: Exception) {
                        _reports.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }

    fun getSummary() {
        viewModelScope.launch {
            if (_summary.value is UiState.Success) {
                return@launch
            }
            reportRepository.getSummary()
                .catch {
                    _summary.value = UiState.Error(it.message.toString())
                }
                .collect { articles ->
                    try {
                        if (!articles.success) {
                            if (articles.message == "Unauthorized") {
                                _summary.value = UiState.Unauthorized
                                return@collect
                            }
                            _summary.value = UiState.Error(articles.message)
                            return@collect
                        }
                        _summary.value = UiState.Success(articles.data)
                    } catch (e: Exception) {
                        _summary.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }

}