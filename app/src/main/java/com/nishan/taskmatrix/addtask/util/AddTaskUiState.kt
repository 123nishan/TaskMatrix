package com.nishan.taskmatrix.addtask.util

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import com.nishan.taskmatrix.addtask.util.ToSaveValidationState
import com.nishan.taskmatrix.util.Priority

data class AddTaskUiState(
    val titleState: TextFieldState = TextFieldState(),
    val descriptionState: TextFieldState = TextFieldState(),
    val timeUiState: TimeUiState,
    val priorityUiState: PriorityUiState = PriorityUiState(),
    val toSaveValidationState: ToSaveValidationState = ToSaveValidationState()
)

// Needs initialization for DatePickerState and TimePickerState
@OptIn(ExperimentalMaterial3Api::class)
data class TimeUiState(
    val datePickerState: DatePickerState,
    val timePickerState: TimePickerState,
    val isTimeSectionExpanded: Boolean = false,
    val isAllDaySelected: Boolean = false,
    val isDatePickerVisible: Boolean = false,
    val isTimePickerVisible: Boolean = false
)

data class PriorityUiState(
    val selectedPriority: Priority = Priority.Low
)