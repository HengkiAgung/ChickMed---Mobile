package bangkit.product.chickmed.ui.screen.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.product.chickmed.data.model.ScheduleModel
import bangkit.product.chickmed.data.repository.ScheduleRepository
import bangkit.product.chickmed.notification.DailyReminder
import bangkit.product.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ScheduleViewModel (
    private val scheduleRepository: ScheduleRepository,
): ViewModel() {
    private val _schedule: MutableStateFlow<UiState<List<ScheduleModel>>> = MutableStateFlow(UiState.Loading)
    val schedule: StateFlow<UiState<List<ScheduleModel>>>
        get() = _schedule

    fun getSchedule() {
        viewModelScope.launch {
            scheduleRepository.getScheduleList()
                .catch {
                    _schedule.value = UiState.Error(it.message.toString())
                }
                .collect { scheduleList ->
                    _schedule.value = UiState.Success(scheduleList)
                }

        }
    }

    fun addSchedule(schedule: ScheduleModel) {
        viewModelScope.launch {
            scheduleRepository.addSchedule(schedule)
        }
    }

    fun deleteSchedule(id: Int) {
        viewModelScope.launch {
            scheduleRepository.deleteSchedule(id)
        }
    }

    fun updateActiveSchedule(id: Int, isActive: Boolean) {
        viewModelScope.launch {
            scheduleRepository.updateActiveSchedule(id, isActive)
        }
    }

    fun updateSchedule(id: Int, title: String, time: String, day: String, isActive: Boolean) {
        viewModelScope.launch {
            scheduleRepository.updateSchedule(id, title, time, day, isActive)
        }
    }
}