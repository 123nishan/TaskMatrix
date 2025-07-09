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
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_5
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nishan.taskmatrix.addtask.components.DateSelector
import com.nishan.taskmatrix.addtask.components.PrioritySelector
import com.nishan.taskmatrix.addtask.components.TimeSelector
import com.nishan.taskmatrix.addtask.util.AddTaskEvents
import com.nishan.taskmatrix.addtask.util.AddTaskUiState
import com.nishan.taskmatrix.addtask.util.DateUiState
import com.nishan.taskmatrix.addtask.util.TimeUiState
import com.nishan.taskmatrix.addtask.util.ToSaveValidationState
import com.nishan.taskmatrix.domain.model.Priority
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme
import com.nishan.taskmatrix.ui.theme.components.TaskMatrixTextField
import com.nishan.taskmatrix.util.convertMillisToDate
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

private fun formatTime(hour: Int, minute: Int): String {
    return String.format("%02d:%02d", hour, minute)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskRoot(
    modifier: Modifier = Modifier,
    addTaskViewModel: AddTaskViewModel = koinViewModel<AddTaskViewModel>(),
    onShowSnackbar: suspend (String, String?) -> Boolean
) {
    val state: AddTaskUiState by addTaskViewModel.uiState.collectAsStateWithLifecycle()
    AddTaskScreen(
        modifier = modifier,
        onEvent = addTaskViewModel::onEvent,
        addTaskUiState = state,
        toSaveValidationState = state.toSaveValidationState
    )
    LaunchedEffect(Unit) {
        addTaskViewModel.snackBarChannel.collect {
          onShowSnackbar(it,null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    onEvent: (AddTaskEvents) -> Unit = {},
    addTaskUiState: AddTaskUiState,
    toSaveValidationState: ToSaveValidationState
) {
    val selectedDate = addTaskUiState.dateUiState.datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""
    val keyboardController = LocalSoftwareKeyboardController.current
    val selectedPriority = addTaskUiState.priorityUiState.selectedPriority

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
                state = addTaskUiState.titleState,
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Title",
                minLines = 1,
            )

            TaskMatrixTextField(
                state = addTaskUiState.descriptionState,
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
                            onEvent(AddTaskEvents.ExpandDateTimeSection)
//                            if (!isExpanded) {
//                                isExpanded = true
//                            }
                        }
                ) {

                    Column {
                        ExpandableSectionTitle(
                            isExpanded = addTaskUiState.isTimeSectionExpanded,
                            title = "Date & Time",
                            closeExpanded = {
                                onEvent(AddTaskEvents.CollapseDateTimeSection)
                            }
                        )
                        AnimatedVisibility(
                            modifier = Modifier
                                .fillMaxWidth(),
                            visible = addTaskUiState.isTimeSectionExpanded
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                            ) {

                                DateSelector(
                                    selectedDate = selectedDate,
                                    isDateSelected = addTaskUiState.dateUiState.isDatePickerVisible,
                                    onDateSelectedChange = {
                                        onEvent(AddTaskEvents.ShowDatePicker)
                                    },
                                )
                                AllDayRow(
                                    allDaySelected = addTaskUiState.isAllDaySelected,
                                    onAllDaySelectedChange = {
                                        onEvent(AddTaskEvents.AllDaySelected(it))
                                    })
                                TimeSelector(
                                    onStartTimeSelectedChange = {
                                        onEvent(AddTaskEvents.ShowStartTimePicker)
                                    },
                                    onEndTimeSelectedChange = {
                                        onEvent(AddTaskEvents.ShowEndTimePicker)
                                    },
                                    isStartTimeSelected = addTaskUiState.startTimeUiState.isTimePickerVisible,
                                    isEndTimeSelected = addTaskUiState.endTimeUiState.isTimePickerVisible,
                                    selectedStartTime = formatTime(
                                        addTaskUiState.startTimeUiState.timePickerState.hour,
                                        addTaskUiState.startTimeUiState.timePickerState.minute
                                    ),
                                    selectedEndTime = formatTime(
                                        addTaskUiState.endTimeUiState.timePickerState.hour,
                                        addTaskUiState.endTimeUiState.timePickerState.minute
                                    ),
                                    isAllDaySelected = addTaskUiState.isAllDaySelected,
                                )

                                AnimatedVisibility(
                                    visible = addTaskUiState.dateUiState.isDatePickerVisible
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
                                            state = addTaskUiState.dateUiState.datePickerState,
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
                                    visible = addTaskUiState.startTimeUiState.isTimePickerVisible
                                ) {
                                    TimePicker(
                                        state = addTaskUiState.startTimeUiState.timePickerState,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                AnimatedVisibility(
                                    visible = addTaskUiState.endTimeUiState.isTimePickerVisible
                                ) {
                                    TimePicker(
                                        state = addTaskUiState.endTimeUiState.timePickerState,
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
                    onEvent(AddTaskEvents.PrioritySelected(it))
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
                onClick = {
                    keyboardController?.hide()
                    onEvent(AddTaskEvents.SaveTask)
                },
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


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    device = PIXEL_5
)
@Composable
private fun AddTaskScreenPreview() {
    TaskMatrixTheme {
        AddTaskScreen(
            modifier = Modifier.fillMaxSize(),
            addTaskUiState = AddTaskUiState(
                isTimeSectionExpanded = true,
                dateUiState = DateUiState(
                    isDatePickerVisible = true,
                    datePickerState = DatePickerState(
                        locale = Locale.getDefault(),
                        initialSelectedDateMillis = System.currentTimeMillis(),
                    )
                ),
                endTimeUiState = TimeUiState(
                    timePickerState = TimePickerState(
                        initialHour = 12,
                        initialMinute = 0,
                        is24Hour = true,

                        )
                ),
                startTimeUiState = TimeUiState(

                    timePickerState = TimePickerState(
                        initialHour = 12,
                        initialMinute = 0,
                        is24Hour = true,

                        )
                )
            ),
            onEvent = {},
            toSaveValidationState = ToSaveValidationState(),
        )
    }
}