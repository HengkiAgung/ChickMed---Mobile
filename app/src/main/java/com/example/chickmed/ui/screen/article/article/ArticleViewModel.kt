package com.example.chickmed.ui.screen.article.article

import android.os.Handler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.repository.ArticleRepository
import com.example.chickmed.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ArticleViewModel (
    private val articleRepository: ArticleRepository,
): ViewModel() {
    private val _articles: MutableStateFlow<UiState<List<ArticleModel>>> = MutableStateFlow(UiState.Loading)
    val articles: StateFlow<UiState<List<ArticleModel>>>
        get() = _articles

    fun getArticles(page: Int) {
        viewModelScope.launch {
            articleRepository.getArticles(page = page)
                .catch {
                    _articles.value = UiState.Error(it.message.toString())
                }
                .collect { articles ->
                    if (!articles.success) {
                        _articles.value = UiState.Error(articles.message)
                        return@collect
                    }
                    _articles.value = UiState.Success(articles.data)
                }
        }
    }
}