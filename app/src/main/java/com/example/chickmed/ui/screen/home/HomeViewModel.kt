package com.example.chickmed.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.model.UserModel
import com.example.chickmed.data.model.faker.FakeDataSource
import com.example.chickmed.data.repository.ArticleRepository
import com.example.chickmed.data.repository.UserRepository
import com.example.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _articles: MutableStateFlow<UiState<List<ArticleModel>>> = MutableStateFlow(UiState.Loading)
    val articles: StateFlow<UiState<List<ArticleModel>>>
        get() = _articles

    private val _user: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val user: StateFlow<UiState<UserModel>>
        get() = _user

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
}