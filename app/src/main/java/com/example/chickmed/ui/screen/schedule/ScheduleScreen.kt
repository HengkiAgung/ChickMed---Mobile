package com.example.chickmed.ui.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
    redirectToWelcome: () -> Unit,
    onScheduleClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    val onSwitchChange = { id: Int, isActive: Boolean ->
        viewModel.updateActiveSchedule(id, isActive)
    }

    viewModel.schedule.collectAsState(initial = UiState.Loading).value.let { schedule ->
        when (schedule) {
            is UiState.Loading -> {
                LoadingIndicator()
                viewModel.getSchedule()
            }

            is UiState.Success -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    FloatingActionButton(
                        onClick = {  },
                        shape = CircleShape,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    ) {
                        Icon(Icons.Filled.Add, "Large floating action button")
                    }

                    LazyColumn (
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        if (schedule.data.isEmpty()) {
                            item {
                                ErrorMessage(message = "No Schedule added")
                            }
                        }
                        items(schedule.data, key = { it.id }) { scheduleData ->
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),

                                modifier = modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onScheduleClick(scheduleData.id)
                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(20.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = scheduleData.title,
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                        Text(
                                            text = scheduleData.time,
                                            style = MaterialTheme.typography.titleSmall,
                                            color = Color.Gray,
                                        )
                                    }
                                    Spacer(modifier = modifier.weight(1f))
                                    Text(
                                        text = "S",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = if (scheduleData.day.contains(
                                                "1",
                                                ignoreCase = true
                                            )
                                        ) Color(
                                            android.graphics.Color.parseColor("#FF8204")
                                        ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "M",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = if (scheduleData.day.contains(
                                                "2",
                                                ignoreCase = true
                                            )
                                        ) Color(
                                            android.graphics.Color.parseColor("#FF8204")
                                        ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "T",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = if (scheduleData.day.contains(
                                                "3",
                                                ignoreCase = true
                                            )
                                        ) Color(
                                            android.graphics.Color.parseColor("#FF8204")
                                        ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "W",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = if (scheduleData.day.contains(
                                                "4",
                                                ignoreCase = true
                                            )
                                        ) Color(
                                            android.graphics.Color.parseColor("#FF8204")
                                        ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "T",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = if (scheduleData.day.contains(
                                                "5",
                                                ignoreCase = true
                                            )
                                        ) Color(
                                            android.graphics.Color.parseColor("#FF8204")
                                        ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "F",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = if (scheduleData.day.contains(
                                                "6",
                                                ignoreCase = true
                                            )
                                        ) Color(
                                            android.graphics.Color.parseColor("#FF8204")
                                        ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "S",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = if (scheduleData.day.contains(
                                                "7",
                                                ignoreCase = true
                                            )
                                        ) Color(
                                            android.graphics.Color.parseColor("#FF8204")
                                        ) else Color(android.graphics.Color.parseColor("#7F4249")),
                                    )
                                    Spacer(modifier = modifier.width(15.dp))
                                    Switch(
                                        checked = scheduleData.isActive,
                                        onCheckedChange = {
                                            onSwitchChange(
                                                scheduleData.id, it
                                            )
                                        }
                                    )
                                }
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

            is UiState.Unauthorized -> {
                redirectToWelcome()
            }
        }
    }
}


