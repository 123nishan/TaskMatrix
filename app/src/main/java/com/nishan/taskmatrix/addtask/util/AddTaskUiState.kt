package com.nishan.taskmatrix.addtask.util

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import com.nishan.taskmatrix.domain.model.Priority


data class AddTaskUiState(
    val titleState: TextFieldState = TextFieldState(),
    val descriptionState: TextFieldState = TextFieldState(),
    val startTimeUiState: TimeUiState,
    val endTimeUiState: TimeUiState,
    val dateUiState: DateUiState,
    val priorityUiState: PriorityUiState = PriorityUiState(),
    val toSaveValidationState: ToSaveValidationState = ToSaveValidationState(),
    val isTimeSectionExpanded: Boolean = false,
    val isAllDaySelected: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

// Needs initialization for DatePickerState and TimePickerState
@OptIn(ExperimentalMaterial3Api::class)
data class TimeUiState(
    val timePickerState: TimePickerState,
    val isTimePickerVisible: Boolean = false,
)

@OptIn(ExperimentalMaterial3Api::class)
data class DateUiState(
    val datePickerState: DatePickerState,
    val isDatePickerVisible: Boolean = false,
)

data class PriorityUiState(
    val selectedPriority: Priority = Priority.Low
)

sealed class AddTaskMessage {
    data class ValidationError(val fields: List<String>) : AddTaskMessage()
    data class DatabaseError(val message: String) : AddTaskMessage()
    object Success : AddTaskMessage()

}