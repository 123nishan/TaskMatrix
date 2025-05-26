package com.nishan.taskmatrix.addtask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_5
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme
import com.nishan.taskmatrix.ui.theme.components.TaskMatrixTextField
import com.nishan.taskmatrix.util.Priority
import com.nishan.taskmatrix.util.convertMillisToDate
import java.util.Calendar

@Composable
fun AddTaskRoot(modifier: Modifier = Modifier) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(modifier: Modifier = Modifier) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var isAllDaySelected by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    var isDateSelected by remember { mutableStateOf(false) }
    var isTimeSelected by remember { mutableStateOf(false) }
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    var selectedPriority by remember { mutableStateOf<Priority>(Priority.Low) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "New Task",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            TaskMatrixTextField(
                state = rememberTextFieldState(), //TODO: update the state in upper composable
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Title",
                minLines = 1,
            )

            TaskMatrixTextField(
                state = rememberTextFieldState(), //TODO: update the state in upper composable
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Description",
                minLines = 5,
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = TextFieldDefaults.MinHeight),
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .clickable {
                            if (!isExpanded) {
                                isExpanded = true
                            }
                        }
                ) {

                    Column {
                        ExpandableSectionTitle(
                            isExpanded = isExpanded,
                            title = "Time",
                            closeExpanded = { isExpanded = false }
                        )
                        AnimatedVisibility(
                            modifier = Modifier
                                .fillMaxWidth(),
                            visible = isExpanded
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                            ) {
                                AllDayRow(
                                    allDaySelected = isAllDaySelected,
                                    onAllDaySelectedChange = {
                                        isAllDaySelected = it
                                    })
                                DateAndTimeSelector(
                                    isAllDaySelected = isAllDaySelected,
                                    selectedDate = selectedDate,
                                    isDateSelected = isDateSelected,
                                    onDateSelectedChange = {
                                        isDateSelected = !isDateSelected
                                        if (isTimeSelected) isTimeSelected = false
                                    },
                                    onTimeSelectedChange = {
                                        isTimeSelected = !isTimeSelected
                                        if (isDateSelected) isDateSelected = false
                                    },
                                    isTimeSelected = isTimeSelected,
                                    selectedTime = "${timePickerState.hour.toString()} : ${timePickerState.minute.toString()}"
                                )

                                AnimatedVisibility(
                                    visible = isDateSelected
                                ) {
                                    BoxWithConstraints(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        val scale = if (this.maxWidth < 360.dp) {
                                            maxWidth / 360.dp
                                        } else {
                                            1f
                                        }
                                        DatePicker(
                                            state = datePickerState,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .requiredWidthIn(min = 360.dp)
                                                .scale(scale),
                                            showModeToggle = false,
                                            title = null,
                                            headline = null
                                        )
                                    }
                                }

                                AnimatedVisibility(
                                    visible = isTimeSelected
                                ) {
                                    TimePicker(
                                        state = timePickerState,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }

                        }
                    }
                }
            }
            PrioritySelector(
                selectedPriority = selectedPriority,
                onPrioritySelectionChange = {
                    selectedPriority = it
                }

            )
            Spacer(
                modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars)
            )

        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(56.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Cancel")
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Save")
            }
        }
    }
}


@Composable
fun DateAndTimeSelector(
    isAllDaySelected: Boolean,
    selectedDate: String = "",
    isDateSelected: Boolean,
    onDateSelectedChange: () -> Unit,
    onTimeSelectedChange: () -> Unit,
    isTimeSelected: Boolean,
    modifier: Modifier = Modifier,
    selectedTime: String
) {

    Row(
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        FilterChip(
            onClick = onDateSelectedChange,
            label = {
                Text(
                    selectedDate,
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            selected = isDateSelected
        )
        Spacer(modifier = Modifier.size(16.dp))
        VerticalDivider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        AnimatedVisibility(!isAllDaySelected) {
            FilterChip(
                onClick = onTimeSelectedChange,
                label = {
                    Text(
                        selectedTime,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                selected = isTimeSelected
            )
        }


    }
}

@Composable
fun AllDayRow(
    modifier: Modifier = Modifier,
    allDaySelected: Boolean,
    onAllDaySelectedChange: (Boolean) -> Unit
) {

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null,
            modifier = Modifier
                .align(
                    Alignment.CenterVertically
                )
        )
        Text(
            text = "All Day",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = allDaySelected,
            onCheckedChange = onAllDaySelectedChange
        )


    }
}

@Composable
fun ExpandableSectionTitle(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    title: String,
    closeExpanded: () -> Unit
) {
    val icon =
        if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CalendarMonth,
            contentDescription = null,
            modifier = Modifier
                .align(
                    Alignment.CenterVertically
                )
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (isExpanded) {
            IconButton(
                onClick = closeExpanded
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = null,

                    )
            }
        }
    }
}


@Preview(
    showBackground = true,
    device = PIXEL_5
)
@Composable
private fun AddTaskScreenPreview() {
    TaskMatrixTheme {
        AddTaskScreen(

        )
    }
}