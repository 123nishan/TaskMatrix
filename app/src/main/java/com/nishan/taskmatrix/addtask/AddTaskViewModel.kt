package com.nishan.taskmatrix.addtask

import android.icu.util.Calendar
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class AddTaskViewModel(): ViewModel() {
    @OptIn(ExperimentalMaterial3Api::class)
    val datePickerState = DatePickerState(
        locale = Locale.getDefault(),
        initialSelectedDateMillis = System.currentTimeMillis(),
    )
    val currentTime = Calendar.getInstance()
    @OptIn(ExperimentalMaterial3Api::class)
    val timePickerState = TimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true, // Or based on user preference
    )
    @OptIn(ExperimentalMaterial3Api::class)
    private val _uiState= MutableStateFlow<AddTaskUiState>(AddTaskUiState(
        timeUiState = TimeUiState(
            datePickerState = datePickerState,
            timePickerState = timePickerState
        )
    ))
    val uiState: StateFlow<AddTaskUiState> get() = _uiState.asStateFlow()

}