package bangkit.product.chickmed.ui.screen.analysis.detail_analysis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
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
import kotlin.math.roundToInt

@Composable
fun DetailAnalysisScreen(
    id: Int,
    redirectToWelcome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailAnalysisViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    viewModel.report.collectAsState(initial = UiState.Loading).value.let { report ->
        when (report) {
            is UiState.Loading -> {
                LoadingIndicator()
                viewModel.getReportById(id)
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = modifier
                        .padding(horizontal = 20.dp)
                ) {
                    item {
                        Row (
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ){
                            AsyncImage(
                                model = report.data.result_image,
                                contentDescription = "Report ${report.data.date} Image",
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(100.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                Text(
                                    text = "Wednesday",
                                    style = MaterialTheme.typography.titleMedium,
                                )
                                Text(
                                    text = report.data.date,
                                    color = Color.Gray,
                                )
                                Text(
                                    text = "Disease Detection",
                                    color = Color.Gray,
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                            }
                        }
                    }

                    items(report.data.report_disease, key = { it.id }) { report ->
                        var confidance = (report.confidence.toFloat() * 100).roundToInt()
                        Column {
//                            Spacer(modifier = Modifier.height(30.dp))
                            Divider(thickness = 1.dp)
                            Spacer(modifier = Modifier.height(30.dp))
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                ),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = Color.White,
                                    //                contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column (
                                    modifier = Modifier.padding(20.dp)
                                ) {
                                    Row {
                                        Text(
                                            text = report.diseases.name,
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Text(
                                            text = "$confidance %",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                    }
                                    Text(
                                        text = report.diseases.description,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Justify
                                    )
                                }
                                Row (
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                ){
                                    Divider(thickness = 1.dp)
                                }
                                Column (
                                    modifier = Modifier.padding(20.dp)
                                ) {
                                    Text(
                                        text = "Solution",
                                        style = MaterialTheme.typography.titleMedium,
                                    )
                                    Text(
                                        text = report.diseases.solution,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Justify
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    }

                }
            }

            is UiState.Error -> {
                ErrorMessage(message = report.errorMessage, modifier = modifier)
            }

            is UiState.Unauthorized -> {
                redirectToWelcome()
            }
        }
    }
}