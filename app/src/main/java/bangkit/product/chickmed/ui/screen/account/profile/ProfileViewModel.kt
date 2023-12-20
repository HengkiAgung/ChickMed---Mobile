package bangkit.product.chickmed.ui.screen.account.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.product.chickmed.data.model.UserModel
import bangkit.product.chickmed.data.repository.UserRepository
import bangkit.product.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val userRepository: UserRepository,
): ViewModel() {
    private val _user: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val user: StateFlow<UiState<UserModel>>
        get() = _user

    fun getUser() {
        viewModelScope.launch {
            if (_user.value is UiState.Success) {
                return@launch
            }
            userRepository.getUserPreference()
                .catch {
                    _user.value = UiState.Error(it.message.toString())
                }
                .collect { user ->
                    try {
                        _user.value = UiState.Success(user)
                    } catch (e: Exception) {
                        _user.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logOut()
        }
    }
}