package com.example.chickmed.ui.screen.schedule

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chickmed.ui.component.article.ArticleItem
import com.example.chickmed.ui.component.respond.ErrorMessage
import com.example.chickmed.ui.component.respond.LoadingIndicator
import com.example.chickmed.ui.screen.ViewModelFactory
import com.example.chickmed.ui.state.UiState

@Composable
fun ScheduleScreen(
    onScheduleClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    viewModel.schedule.collectAsState(initial = UiState.Loading).value.let { schedule ->
        when (schedule) {
            is UiState.Loading -> {
                LoadingIndicator()
            }

            is UiState.Success -> {
                LazyColumn {
                    if (schedule.data.isEmpty()) {
                        item {
                            ErrorMessage(message = "No Schedule added")
                        }
                    }
                    items(schedule.data, key = { it.id }) {
                        val scheduleData = it
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
                                .clickable {
                                    onScheduleClick(scheduleData.id)
                                }
                        ) {
                            Row {
                                Column {
                                    Text(
                                        text = scheduleData.title,
                                        style = MaterialTheme.typography.titleMedium,
                                    )
                                    Text(
                                        text = scheduleData.time,
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                }
                                Spacer(modifier = modifier.width(10.dp))
                                Text(
                                    text = "S",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (scheduleData.day.contains("1", ignoreCase = true)) Color(
                                        android.graphics.Color.parseColor("#FF8204")
                                    ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                )
                                Text(
                                    text = "M",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (scheduleData.day.contains("2", ignoreCase = true)) Color(
                                        android.graphics.Color.parseColor("#FF8204")
                                    ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                )
                                Text(
                                    text = "T",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (scheduleData.day.contains("3", ignoreCase = true)) Color(
                                        android.graphics.Color.parseColor("#FF8204")
                                    ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                )
                                Text(
                                    text = "W",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (scheduleData.day.contains("4", ignoreCase = true)) Color(
                                        android.graphics.Color.parseColor("#FF8204")
                                    ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                )
                                Text(
                                    text = "T",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (scheduleData.day.contains("5", ignoreCase = true)) Color(
                                        android.graphics.Color.parseColor("#FF8204")
                                    ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                )
                                Text(
                                    text = "F",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (scheduleData.day.contains("6", ignoreCase = true)) Color(
                                        android.graphics.Color.parseColor("#FF8204")
                                    ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                )
                                Text(
                                    text = "S",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (scheduleData.day.contains("7", ignoreCase = true)) Color(
                                        android.graphics.Color.parseColor("#FF8204")
                                    ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                )
                                Switch(checked = scheduleData.isActive, onCheckedChange = {onSwitchChange(scheduleData.id)})
                            }
                        }
                    }
                }
            }

            is UiState.Error -> {
                ErrorMessage(
                    message = schedule.errorMessage,
                    modifier = modifier
                )
            }
        }
    }
}

fun onSwitchChange(id: Int) {
    viewModel.updateSchedule(id)
}
