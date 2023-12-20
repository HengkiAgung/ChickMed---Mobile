package bangkit.product.chickmed.ui.screen.account.my_account

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.product.chickmed.data.model.UserModel
import bangkit.product.chickmed.data.repository.UserRepository
import bangkit.product.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File

class MyAccountViewModel (
    private val userRepository: UserRepository
): ViewModel() {
    private val _user: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val user: StateFlow<UiState<UserModel>>
        get() = _user

    fun getUser() {
        _user.value = UiState.Loading
        viewModelScope.launch {
            userRepository.getUser()
                .catch {
                    _user.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    try {
                        if (!data.success) {
                            if (data.message == "Unauthorized") {
                                _user.value = UiState.Unauthorized
                                return@collect
                            }
                            _user.value = UiState.Error(data.message)
                            return@collect
                        }
                        _user.value = UiState.Success(data.data)
                    } catch (e: Exception) {
                        _user.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }

    fun updateUser(name: String, profile: File?, email: String) {
        _user.value = UiState.Loading
        viewModelScope.launch {
            userRepository.updateUser(name = name, profile = profile, email = email)
                .catch {
                    _user.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    try {
                        if (!data.success) {
                            _user.value = UiState.Error(data.message)
                            return@collect
                        }
                        _user.value = UiState.Success(data.data)
                    } catch (e: Exception) {
                        _user.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }
}