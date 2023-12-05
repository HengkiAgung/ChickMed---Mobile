package com.example.chickmed.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.chickmed.R
import com.example.chickmed.data.di.Injection
import com.example.chickmed.ui.component.article.ArticleItem
import com.example.chickmed.ui.component.ButtonActionMenu
import com.example.chickmed.ui.component.respond.ErrorMessage
import com.example.chickmed.ui.component.respond.LoadingIndicator
import com.example.chickmed.ui.screen.ViewModelFactory
import com.example.chickmed.ui.state.UiState

@Composable
fun HomeScreen(
    name: String,
    image: String,
    onFindDiseaseClick: () -> Unit = {},
    onScheduleClick: () -> Unit = {},
    onInfoDiseaseClick: () -> Unit = {},
    onArticleClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#FFFFFF")),
                        Color(android.graphics.Color.parseColor("#FF8204")),
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp, top = 50.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Welcome !",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = name,
            )
            Text(
                text = "How are you today?",
            )
        }
        Column(
            modifier = modifier
                .clip(
                    MaterialTheme.shapes.large.copy(
                        bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)
                    )
                )
                .background(Color.White)
                .padding(start = 20.dp, top = 50.dp, end = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonActionMenu(
                    text = "Find Disease",
                    icon = R.drawable.stethoscope,
                    onClick = onFindDiseaseClick,
                )
                ButtonActionMenu(
                    text = "Schedule",
                    icon = R.drawable.clock,
                    onClick = onScheduleClick,
                )
                ButtonActionMenu(
                    text = "Info Disease",
                    icon = R.drawable.newspaper,
                    onClick = onInfoDiseaseClick,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            viewModel.articles.collectAsState(initial = UiState.Loading).value.let { articles ->
                when (articles){
                    is UiState.Loading -> {
                        LoadingIndicator()
                        viewModel.getArticles()
                    }

                    is UiState.Success -> {
                        Row {
                            Text(
                                text = "Article",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = modifier
                                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "See All",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = modifier
                                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                            )
                        }
                        articles.data.map { article ->
                            ArticleItem(
                                title = article.title,
                                date = article.date,
                                image = article.image,
                                onNavigateToDetailScreen = onArticleClick,
                                id = article.id,
                            ) }
                    }

                    is UiState.Error -> {
                        ErrorMessage(message = articles.errorMessage)
                    }
                }
            }
        }
    }
}