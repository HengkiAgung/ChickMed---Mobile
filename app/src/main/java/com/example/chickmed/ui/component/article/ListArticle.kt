package com.example.chickmed.ui.component.article

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.ui.component.respond.ErrorMessage

@Composable
fun ListArticle (
    articles: List<ArticleModel>,
    onNavigateToDetailScreen: (Int) -> Unit,
) {
    LazyColumn {
        if (articles.isEmpty()) {
            item {
                ErrorMessage(message = "No article found")
            }
        }
        items(articles, key = { it.id }) {
            ArticleItem(
                id = it.id,
                title = it.title,
                image = it.image,
                date = it.date,
                onNavigateToDetailScreen = onNavigateToDetailScreen,
            )
        }
    }
}