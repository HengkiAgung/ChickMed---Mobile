package bangkit.product.chickmed

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.product.chickmed.data.model.UserModel
import bangkit.product.chickmed.data.repository.UserRepository
import bangkit.product.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel (
    private val userRepository: UserRepository
): ViewModel() {
    private val _isHaveToken: MutableState<UiState<Boolean>> = mutableStateOf(UiState.Loading)
    val isHaveToken: MutableState<UiState<Boolean>>
        get() = _isHaveToken

    fun checkToken() {
        viewModelScope.launch {
            _isHaveToken.value = UiState.Success(userRepository.getToken().isNotEmpty())
        }
    }
}