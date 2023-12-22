package bangkit.product.chickmed.ui.screen.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bangkit.product.chickmed.data.model.ScheduleModel
import bangkit.product.chickmed.notification.DailyReminder
import bangkit.product.chickmed.ui.component.ButtonActionMenu
import bangkit.product.chickmed.ui.screen.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScheduleScreen(
    onSave: () -> Unit,
    viewModel: ScheduleViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {

    // Previously implemented code.
    var title by rememberSaveable { mutableStateOf("") }
    val time = rememberTimePickerState(is24Hour = true)

    var sundaySelected by rememberSaveable { mutableStateOf(false) }
    var mondaySelected by rememberSaveable { mutableStateOf(false) }
    var tuesdaySelected by rememberSaveable { mutableStateOf(false) }
    var wednesdaySelected by rememberSaveable { mutableStateOf(false) }
    var thursdaySelected by rememberSaveable { mutableStateOf(false) }
    var fridaySelected by rememberSaveable { mutableStateOf(false) }
    var saturdaySelected by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Add Alarm Schedule",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Name",
            style = MaterialTheme.typography.bodyLarge
        )
        TextField(value = title, onValueChange = { title = it }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = "Time",
            style = MaterialTheme.typography.bodyLarge
        )
        TimeInput(
            state = time,
            modifier = Modifier.height(100.dp),
        )

        Text(
            text = "Select Days",
            style = MaterialTheme.typography.bodyLarge
        )
        var selectionCount by rememberSaveable { mutableStateOf(0) }
        val context = LocalContext.current

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selected = sundaySelected,
                onClick = {
                    handleSelection(
                        isSelected = sundaySelected,
                        selectionCount = selectionCount,
                        onStateChange = { count, selected ->
                            sundaySelected = selected
                            selectionCount = count
                        },
                    )
                },
                label = { Text("Sunday") },
            )

            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selected = mondaySelected,
                onClick = {
                    handleSelection(
                        isSelected = mondaySelected,
                        selectionCount = selectionCount,
                        onStateChange = { count, selected ->
                            mondaySelected = selected
                            selectionCount = count
                        },
                    )
                },
                label = { Text(text = "Monday") },
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selected = tuesdaySelected,
                onClick = {
                    handleSelection(
                        isSelected = tuesdaySelected,
                        selectionCount = selectionCount,
                        onStateChange = { count, selected ->
                            tuesdaySelected = selected
                            selectionCount = count
                        },
                    )
                },
                label = { Text(text = "Tuesday") },
            )

            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selected = wednesdaySelected,
                onClick = {
                    handleSelection(
                        isSelected = wednesdaySelected,
                        selectionCount = selectionCount,
                        onStateChange = { count, selected ->
                            wednesdaySelected = selected
                            selectionCount = count
                        },
                    )
                },
                label = { Text(text = "Wednesday") },
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selected = thursdaySelected,
                onClick = {
                    handleSelection(
                        isSelected = thursdaySelected,
                        selectionCount = selectionCount,
                        onStateChange = { count, selected ->
                            thursdaySelected = selected
                            selectionCount = count
                        },
                    )
                },
                label = { Text(text = "Thursday") },
            )

            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selected = fridaySelected,
                onClick = {
                    handleSelection(
                        isSelected = fridaySelected,
                        selectionCount = selectionCount,
                        onStateChange = { count, selected ->
                            fridaySelected = selected
                            selectionCount = count
                        },
                    )
                },
                label = { Text(text = "Friday") },
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selected = saturdaySelected,
                onClick = {
                    handleSelection(
                        isSelected = saturdaySelected,
                        selectionCount = selectionCount,
                        onStateChange = { count, selected ->
                            saturdaySelected = selected
                            selectionCount = count
                        },
                    )
                },
                label = { Text(text = "Saturday") },
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),)
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                var day = ""
                if (sundaySelected) {
                    day += "1 "
                }
                if (mondaySelected) {
                    day += "2 "
                }
                if (tuesdaySelected) {
                    day += "3 "
                }
                if (wednesdaySelected) {
                    day += "4 "
                }
                if (thursdaySelected) {
                    day += "5 "
                }
                if (fridaySelected) {
                    day += "6 "
                }
                if (saturdaySelected) {
                    day += "7 "
                }

                viewModel.addSchedule(
                    ScheduleModel(
                        title = title,
                        time = time.hour.toString() + ":" + time.minute.toString(),
                        day = day,
                        isActive = true
                    )
                )
                onSave()
            },
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(
                text = "Save",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

private fun handleSelection(
    isSelected: Boolean,
    selectionCount: Int,
    onStateChange: (Int, Boolean) -> Unit,
) {
    if (isSelected) {
        onStateChange(selectionCount - 1, !isSelected)
    } else {
        onStateChange(selectionCount + 1, !isSelected)
    }
}