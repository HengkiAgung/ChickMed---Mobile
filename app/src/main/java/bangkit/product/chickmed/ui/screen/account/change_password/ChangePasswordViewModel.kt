package bangkit.product.chickmed.ui.screen.account.change_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.product.chickmed.data.repository.UserRepository
import bangkit.product.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ChangePasswordViewModel (
    private val userRepository: UserRepository
): ViewModel() {
    private val _isChange: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Loading)
    val isChange: MutableStateFlow<UiState<Boolean>>
        get() = _isChange

    fun changePassword(newPassword: String, confirmPassword: String) {
        _isChange.value = UiState.Loading
        viewModelScope.launch {
            userRepository.changePassword(newPassword = newPassword, confirmPassword = confirmPassword)
                .catch {
                    _isChange.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    try {
                        if (!data.success) {
                            if (data.message == "Unauthorized") {
                                _isChange.value = UiState.Unauthorized
                                return@collect
                            }
                            _isChange.value = UiState.Error(data.message)
                            return@collect
                        }
                        _isChange.value = UiState.Success(data.data)
                    } catch (e: Exception) {
                        _isChange.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }
}