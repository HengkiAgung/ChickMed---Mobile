package bangkit.product.chickmed.ui.screen.article.article

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bangkit.product.chickmed.activity.ArticleActivity
import bangkit.product.chickmed.activity.DetailArticleActivity
import bangkit.product.chickmed.ui.component.article.ArticleItem
import bangkit.product.chickmed.ui.component.article.ListArticle
import bangkit.product.chickmed.ui.component.respond.ErrorMessage
import bangkit.product.chickmed.ui.component.respond.LoadingIndicator
import bangkit.product.chickmed.ui.screen.ViewModelFactory
import bangkit.product.chickmed.ui.state.UiState

@Composable
fun ArticleScreen(
    redirectToWelcome: () -> Unit,
    viewModel: ArticleViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    modifier: Modifier = Modifier,
) {
    val activity = LocalContext.current as Activity
    val context = LocalContext.current


    viewModel.articles.collectAsState(initial = UiState.Loading).value.let { articles ->
        when (articles) {
            is UiState.Loading -> {
                LoadingIndicator(modifier = modifier)
                viewModel.getArticles(1)
            }

            is UiState.Success -> {
                if (articles.data.isNullOrEmpty()) ErrorMessage(message = "Data is empty")
                else ListArticle(articles = articles.data, onNavigateToDetailScreen = { id ->
                    activity.startActivity(
                        Intent(context, DetailArticleActivity::class.java).putExtra("id_article", id)
                    )
                }, modifier = modifier)
            }

            is UiState.Error -> {
                ErrorMessage(message = articles.errorMessage, modifier = modifier)
            }

            is UiState.Unauthorized -> {
                redirectToWelcome()
            }
        }
    }
}