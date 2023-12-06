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

    fun getArticles() {
        viewModelScope.launch {
            userRepository.getUser()
//            userRepository.saveUser(FakeDataSource.dummyUser.id, FakeDataSource.dummyUser.name, FakeDataSource.dummyUser.profile, FakeDataSource.dummyUser.token)
            articleRepository.getArticles("")
                .catch {
                    _articles.value = UiState.Error(it.message.toString())
                }
                .collect { articles ->
                    _articles.value = UiState.Success(articles.take(3))
                }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser()
                .catch {
                    _user.value = UiState.Error(it.message.toString())
                }
                .collect { user ->
                    _user.value = UiState.Success(user)
                }
        }
    }
}