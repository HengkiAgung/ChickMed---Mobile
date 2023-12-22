package bangkit.product.chickmed.ui.screen.article.detail_article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import bangkit.product.chickmed.ui.component.respond.ErrorMessage
import bangkit.product.chickmed.ui.component.respond.LoadingIndicator
import bangkit.product.chickmed.ui.screen.ViewModelFactory
import bangkit.product.chickmed.ui.state.UiState

@Composable
fun DetailArticleScreen(
    id: Int,
    redirectToWelcome: () -> Unit,
    viewModel: DetailArticleViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    viewModel.article.collectAsState(initial = UiState.Loading).value.let { article ->
        when (article) {
            is UiState.Loading -> {
                LoadingIndicator(modifier = modifier)
                viewModel.getArticleById(id)
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    item {
                        article.data.apply {
                            AsyncImage(
                                model = image,
                                contentDescription = "$title Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(346.dp),
                                alignment = Alignment.CenterStart,
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    item {
                        article.data.apply {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(20.dp)
                            ) {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = date,
                                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                        }
                    }

                    // My story details
                    item {
                        article.data.apply {

                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = "Description",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = content,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }

            is UiState.Error -> {
                ErrorMessage(message = article.errorMessage, modifier = modifier)
            }

            is UiState.Unauthorized -> {
                redirectToWelcome()
            }
        }
    }

}