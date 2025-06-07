package com.nishan.taskmatrix.addtask

import android.icu.util.Calendar
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishan.taskmatrix.addtask.util.AddTaskEvents
import com.nishan.taskmatrix.addtask.util.AddTaskUiState
import com.nishan.taskmatrix.addtask.util.TimeUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
class AddTaskViewModel() : ViewModel() {

    private val _snackBarChannel = Channel<String>(Channel.BUFFERED)
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

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
    private val _uiState = MutableStateFlow<AddTaskUiState>(
        AddTaskUiState(
            timeUiState = TimeUiState(
                datePickerState = datePickerState,
                timePickerState = timePickerState
            )
        )
    )
    val uiState: StateFlow<AddTaskUiState> get() = _uiState.asStateFlow()


    @OptIn(ExperimentalMaterial3Api::class)
    fun onEvent(event: AddTaskEvents) {
        when (event) {
            AddTaskEvents.SaveTask -> {
                val titleSize = _uiState.value.titleState.text.length
                val descriptionSize = _uiState.value.descriptionState.text.length
                _uiState.update {
                    currentState ->
                    currentState.copy(
                        toSaveValidationState = currentState.toSaveValidationState.copy(
                            hasTitle = titleSize > 0,
                            hasDescription = descriptionSize > 0,
                        )
                    )
                }
               if(!_uiState.value.toSaveValidationState.isValidSave) {

                   viewModelScope.launch {
                       val toSaveValidationState = _uiState.value.toSaveValidationState
                       val missingFields = buildList {
                           if (!toSaveValidationState.hasTitle) add("Title")
                           if (!toSaveValidationState.hasDescription) add("Description")
                       }
                       if(missingFields.isNotEmpty()) {
                           val message = "Please check the following fields: ${missingFields.joinToString(", ")}"
                           _snackBarChannel.send(message)
                       }
                   }
               }
            }

            AddTaskEvents.ShowDatePicker -> {
                _uiState.update { currentState ->
                    val currentTimeUiState = currentState.timeUiState
                    currentState.copy(
                        timeUiState = currentTimeUiState.copy(
                            isDatePickerVisible = !currentTimeUiState.isDatePickerVisible,
                            isTimePickerVisible = if (currentTimeUiState.isTimePickerVisible) false else currentTimeUiState.isTimePickerVisible
                        )
                    )

                }
            }

            AddTaskEvents.ShowPriorityPicker -> TODO()
            AddTaskEvents.ShowTimePicker -> {
                _uiState.update { currentState ->
                    val currentTimeUiState = currentState.timeUiState
                    currentState.copy(
                        timeUiState = currentTimeUiState.copy(
                            isTimePickerVisible = !currentTimeUiState.isTimePickerVisible,
                            isDatePickerVisible = if (currentTimeUiState.isDatePickerVisible) false else currentTimeUiState.isDatePickerVisible
                        )
                    )
                }
            }

            is AddTaskEvents.AllDaySelected -> {
                _uiState.update { currentState ->
                    val currentTimeUiState = currentState.timeUiState
                    currentState.copy(
                        timeUiState = currentTimeUiState.copy(
                            isAllDaySelected = event.value
                        )
                    )
                }
            }

            AddTaskEvents.ExpandDateTimeSection -> {
                _uiState.update { currentState ->
                    val currentTimeUiState = currentState.timeUiState
                    currentState.copy(
                        timeUiState = currentTimeUiState.copy(
                            isTimeSectionExpanded = true
                        )
                    )
                }
            }

            AddTaskEvents.CollapseDateTimeSection -> {
                _uiState.update { currentState ->
                    val currentTimeUiState = currentState.timeUiState
                    currentState.copy(
                        timeUiState = currentTimeUiState.copy(
                            isTimeSectionExpanded = false
                        )
                    )
                }
            }
        }
    }

}