package bangkit.product.chickmed.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.product.chickmed.data.model.ArticleModel
import bangkit.product.chickmed.data.model.UserModel
import bangkit.product.chickmed.data.model.faker.FakeDataSource
import bangkit.product.chickmed.data.repository.ArticleRepository
import bangkit.product.chickmed.data.repository.ReportRepository
import bangkit.product.chickmed.data.repository.UserRepository
import bangkit.product.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File

class HomeViewModel(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository,
    private val reportRepository: ReportRepository
): ViewModel() {
    private val _articles: MutableStateFlow<UiState<List<ArticleModel>>> = MutableStateFlow(UiState.Loading)
    val articles: StateFlow<UiState<List<ArticleModel>>>
        get() = _articles

    private val _user: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val user: StateFlow<UiState<UserModel>>
        get() = _user

    private val _isAnalisysDone: MutableStateFlow<UiState<Int>> = MutableStateFlow(UiState.Loading)
    val isAnalisysDone: StateFlow<UiState<Int>>
        get() = _isAnalisysDone

    fun getArticles(page: Int) {
        viewModelScope.launch {
            if (_articles.value is UiState.Success) {
                return@launch
            }
            articleRepository.getArticles(page = page)
                .catch {
                    _articles.value = UiState.Error(it.message.toString())
                }
                .collect { articles ->
                    try {
                        if (!articles.success) {
                            if (articles.message == "Unauthorized") {
                                _articles.value = UiState.Unauthorized
                                return@collect
                            }
                            _articles.value = UiState.Error(articles.message)
                            return@collect
                        }
                        _articles.value = UiState.Success(articles.data.take(3))
                    } catch (e: Exception) {
                        _articles.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }

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

    fun doAnalysis(image: File) {
        _isAnalisysDone.value = UiState.Loading
        viewModelScope.launch {
            reportRepository.doAnalysis(image = image)
                .catch {
                    _isAnalisysDone.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    try {
                        if (!data.success) {
                            _isAnalisysDone.value = UiState.Error(data.message)
                            return@collect
                        }
                        _isAnalisysDone.value = UiState.Success(200)
                    } catch (e: Exception) {
                        _isAnalisysDone.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }
}