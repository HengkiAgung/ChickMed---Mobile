package com.example.chickmed.ui.screen.article.detail_article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.repository.ArticleRepository
import com.example.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailArticleViewModel (
    private val articleRepository: ArticleRepository,
): ViewModel() {
    private val _article: MutableStateFlow<UiState<ArticleModel>> = MutableStateFlow(UiState.Loading)
    val article: StateFlow<UiState<ArticleModel>>
        get() = _article

    fun getArticleById(id: Int) {
        viewModelScope.launch {
            articleRepository.getArticleById(id)
                .catch {
                    _article.value = UiState.Error(it.message.toString())
                }
                .collect { article ->
                    try {
                        if (!article.success) {
                            if (article.message == "Unauthorized") {
                                _article.value = UiState.Unauthorized
                                return@collect
                            }
                            _article.value = UiState.Error(article.message)
                            return@collect
                        }
                        _article.value = UiState.Success(article.data)
                    } catch (e: Exception) {
                        _article.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }
}