package com.example.chickmed.ui.screen.analysis.report

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.chickmed.R
import com.example.chickmed.activity.DetailAnalysisActivity
import com.example.chickmed.activity.DetailArticleActivity
import com.example.chickmed.ui.component.article.ArticleItem
import com.example.chickmed.ui.component.respond.ErrorMessage
import com.example.chickmed.ui.component.respond.LoadingIndicator
import com.example.chickmed.ui.screen.ViewModelFactory
import com.example.chickmed.ui.state.UiState
import com.example.chickmed.ui.theme.ChickMedTheme

@Composable
fun ReportsScreen(
    modifier: Modifier = Modifier,
    viewModel: ReportViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    val activity = LocalContext.current as Activity
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(start = 20.dp, top = 20.dp, end = 20.dp)

    ) {
        viewModel.summary.collectAsState(initial = UiState.Loading).value.let { summary ->
            when (summary) {
                is UiState.Loading -> {
                    LoadingIndicator()
                    viewModel.getSummary()
                }

                is UiState.Success -> {
                    SummaryContent(
                        detection_in_a_month = summary.data.detection_in_a_month.toString(),
                        sick_chicken = summary.data.sick_chicken.toString(),
                        healthy = summary.data.healthy.toString(),
                    )
                }

                is UiState.Error -> {
                    ErrorMessage(message = summary.errorMessage)
                }
            }

        }
        Spacer(modifier = modifier.height(20.dp))
        Text(
            text = "Latest report",
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
        )
        viewModel.reports.collectAsState(initial = UiState.Loading).value.let { reports ->
            when (reports) {
                is UiState.Loading -> {
                    LoadingIndicator()
                    viewModel.getReports()
                }

                is UiState.Success -> {
                    LazyColumn {
                        if (reports.data.isEmpty()) {
                            item {
                                ErrorMessage(message = "No Report found")
                            }
                        }
                        items(reports.data, key = { it.id }) {

                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),

                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 10.dp,
                                        bottom = 8.dp,
                                        start = 10.dp,
                                        end = 10.dp,
                                    )
                                    .background(Color.White)
                                    .clickable {
                                        activity.startActivity(
                                            Intent(context, DetailAnalysisActivity::class.java).putExtra("id_report", it.id)
                                        )
                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.report_placeholder),
                                        contentDescription = "Report ${it.date}",
                                        contentScale = ContentScale.Crop,
                                        modifier = modifier
                                            .padding(4.dp)
                                            .size(100.dp)
                                            .clip(MaterialTheme.shapes.medium)
                                    )
                                    Column(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    ) {
                                        Text(
                                            text = "${it.diseases.count().toString()} Diseases",
                                            fontSize = 15.sp,
                                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                                            modifier = modifier.testTag("ArticleTitle")
                                        )
                                        Text(
                                            text = it.date,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Light,
                                            fontStyle = FontStyle.Italic,
                                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                                            modifier = modifier
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                is UiState.Error -> {
                    ErrorMessage(message = reports.errorMessage)
                }
            }
        }
    }
}

@Composable
fun SummaryContent(
    detection_in_a_month: String,
    sick_chicken: String,
    healthy: String,
    modifier: Modifier = Modifier,
) {
    Column {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),

            modifier = modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 8.dp,
                    start = 10.dp,
                    end = 10.dp,
                )
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = "Detection in a month",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .padding(10.dp)
                    )
                    Row {
                        Text(
                            text = detection_in_a_month,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                                .padding(10.dp)
                        )
                        Text(
                            text = "Report",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = modifier
                                .padding(10.dp)
                        )
                    }
                }
                Icon(
                    painter = painterResource(R.drawable.chart_simple),
                    contentDescription = "Detection in a month $detection_in_a_month Report",
                    modifier = modifier
                        .padding(10.dp)
                        .size(70.dp)
                )
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),

                modifier = modifier
                    .padding(
                        top = 10.dp,
                        bottom = 8.dp,
                        start = 10.dp,
                        end = 10.dp,
                    )
                    .background(Color.White)
                    .weight(1f)
            ) {
                Column {
                    Icon(
                        painter = painterResource(R.drawable.virus),
                        contentDescription = "Sick Chicken $sick_chicken Chick",
                        modifier = modifier
                            .padding(10.dp)
                            .size(50.dp)
                    )
                    Text(
                        text = "Sick Chicken",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .padding(10.dp)
                    )
                    Row {
                        Text(
                            text = sick_chicken,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                                .padding(10.dp)
                        )
                        Text(
                            text = "Chick",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = modifier
                                .padding(10.dp)
                        )
                    }
                }
            }
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),

                modifier = modifier
                    .padding(
                        top = 10.dp,
                        bottom = 8.dp,
                        start = 10.dp,
                        end = 10.dp,
                    )
                    .background(Color.White)
                    .weight(1f)
            ) {
                Column {
                    Icon(
                        painter = painterResource(R.drawable.heart),
                        contentDescription = "Healthy Chicken $healthy Chick",
                        modifier = modifier
                            .padding(10.dp)
                            .size(50.dp)
                    )
                    Text(
                        text = "Healthy",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .padding(10.dp)
                    )
                    Row {
                        Text(
                            text = healthy,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                                .padding(10.dp)
                        )
                        Text(
                            text = "Chick",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = modifier
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}