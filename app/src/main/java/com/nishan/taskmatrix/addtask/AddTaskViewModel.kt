package com.nishan.taskmatrix.addtask

import android.icu.util.Calendar
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishan.taskmatrix.addtask.util.AddTaskEvents
import com.nishan.taskmatrix.addtask.util.AddTaskUiState
import com.nishan.taskmatrix.addtask.util.DateUiState
import com.nishan.taskmatrix.addtask.util.TimeUiState
import com.nishan.taskmatrix.domain.model.Task
import com.nishan.taskmatrix.domain.repository.TaskRepository
import com.nishan.taskmatrix.domain.toEpochMillis
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
class AddTaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _snackBarChannel = Channel<String>(Channel.BUFFERED)
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

    @OptIn(ExperimentalMaterial3Api::class)
    val datePickerState = DatePickerState(
        locale = Locale.getDefault(),
        initialSelectedDateMillis = System.currentTimeMillis(),
    )
    val currentTime = Calendar.getInstance()


    @OptIn(ExperimentalMaterial3Api::class)
    val startTimePickerState = TimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true, // Or based on user preference
    )

    @OptIn(ExperimentalMaterial3Api::class)
    val endTimePickerState = TimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true, // Or based on user preference
    )

    @OptIn(ExperimentalMaterial3Api::class)
    private val _uiState = MutableStateFlow<AddTaskUiState>(
        AddTaskUiState(
            startTimeUiState = TimeUiState(
                timePickerState = startTimePickerState
            ),
            endTimeUiState = TimeUiState(
                timePickerState = endTimePickerState
            ),
            dateUiState = DateUiState(
                datePickerState = datePickerState
            )
        )
    )
    val uiState: StateFlow<AddTaskUiState> get() = _uiState.asStateFlow()


    @OptIn(ExperimentalMaterial3Api::class)
    fun onEvent(event: AddTaskEvents) {
        when (event) {
            AddTaskEvents.SaveTask -> {
                val title = _uiState.value.titleState.text.toString().trim()
                val description = _uiState.value.descriptionState.text.toString().trim()
                _uiState.update { currentState ->
                    currentState.copy(
                        toSaveValidationState = currentState.toSaveValidationState.copy(
                            hasTitle = title.isNotEmpty(),
                            hasDescription = description.isNotEmpty(),
                        )
                    )
                }
                if (!_uiState.value.toSaveValidationState.isValidSave) {

                    viewModelScope.launch {
                        val toSaveValidationState = _uiState.value.toSaveValidationState
                        val missingFields = buildList {
                            if (!toSaveValidationState.hasTitle) add("Title")
                            if (!toSaveValidationState.hasDescription) add("Description")
                        }
                        if (missingFields.isNotEmpty()) {
                            val message =
                                "Please check the following fields: ${missingFields.joinToString(", ")}"
                            _snackBarChannel.send(message)
                        }
                    }
                } else {
                    viewModelScope.launch {
                        val title = _uiState.value.titleState.text.toString().trim()
                        val description = _uiState.value.descriptionState.text.toString().trim()
                        val priority = _uiState.value.priorityUiState.selectedPriority
                        val date = _uiState.value.dateUiState.datePickerState.selectedDateMillis
                        val startHour = _uiState.value.startTimeUiState.timePickerState.hour
                        val endHour = _uiState.value.endTimeUiState.timePickerState.hour
                        val startMinute = _uiState.value.startTimeUiState.timePickerState.minute
                        val endMinute = _uiState.value.endTimeUiState.timePickerState.minute
                        if (_uiState.value.isAllDaySelected) {
                            val task = Task(
                                title = title,
                                description = description,
                                priority = priority,
                                date = date,
                                startTime = null,
                                endTime = null,
                                isAllDay = true
                            )
                            println(task.toString())
                            taskRepository.insertTask(
                                task
                            )
                        } else {
                            val task = Task(
                                title = title,
                                description = description,
                                priority = priority,
                                date = date,
                                startTime = date?.let {
                                    toEpochMillis(it, startHour, startMinute)
                                },
                                endTime = date?.let {
                                    toEpochMillis(it, endHour, endMinute)
                                },
                                isAllDay = false
                            )
                            println(task.toString())
                            taskRepository.insertTask(
                                task
                            )
                            _snackBarChannel.send("Data inserted successfully")
                        }
                    }
                }
            }

            AddTaskEvents.ShowDatePicker -> {
                _uiState.update { currentState ->
                    val currentUiState = currentState
                    currentState.copy(
                        dateUiState = currentUiState.dateUiState.copy(
                            isDatePickerVisible = !currentUiState.dateUiState.isDatePickerVisible
                        ),
                        startTimeUiState = currentUiState.startTimeUiState.copy(
                            isTimePickerVisible = if (currentUiState.startTimeUiState.isTimePickerVisible) false else currentUiState.startTimeUiState.isTimePickerVisible

                        ),
                        endTimeUiState = currentUiState.endTimeUiState.copy(
                            isTimePickerVisible = if (currentUiState.endTimeUiState.isTimePickerVisible) false else currentUiState.endTimeUiState.isTimePickerVisible
                        )
                    )

                }
            }


            is AddTaskEvents.AllDaySelected -> {
                _uiState.update { currentState ->
                    val currentUiState = currentState
                    currentState.copy(
                        isAllDaySelected = event.value
                    )
                }
            }

            AddTaskEvents.ExpandDateTimeSection -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isTimeSectionExpanded = true
                    )
                }
            }

            AddTaskEvents.CollapseDateTimeSection -> {
                _uiState.update { currentState ->

                    currentState.copy(
                        isTimeSectionExpanded = false
                    )
                }
            }

            is AddTaskEvents.PrioritySelected -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        priorityUiState = currentState.priorityUiState.copy(
                            selectedPriority = event.priority
                        )
                    )
                }
            }

            AddTaskEvents.ShowStartTimePicker -> {
                _uiState.update { currentState ->
                    val currentUiState = currentState
                    currentState.copy(
                        startTimeUiState = currentUiState.startTimeUiState.copy(
                            isTimePickerVisible = !currentUiState.startTimeUiState.isTimePickerVisible
                        ),
                        endTimeUiState = currentState.endTimeUiState.copy(
                            isTimePickerVisible = if (currentUiState.endTimeUiState.isTimePickerVisible) false else currentUiState.endTimeUiState.isTimePickerVisible
                        ),
                        dateUiState = currentUiState.dateUiState.copy(
                            isDatePickerVisible = if (currentUiState.dateUiState.isDatePickerVisible) false else currentUiState.dateUiState.isDatePickerVisible
                        )
                    )
                }
            }

            AddTaskEvents.ShowEndTimePicker -> {
                _uiState.update { currentState ->
                    val currentUiState = currentState
                    currentState.copy(
                        endTimeUiState = currentUiState.endTimeUiState.copy(
                            isTimePickerVisible = !currentUiState.endTimeUiState.isTimePickerVisible
                        ),
                        startTimeUiState = currentUiState.startTimeUiState.copy(
                            isTimePickerVisible = if (currentUiState.startTimeUiState.isTimePickerVisible) false else currentUiState.startTimeUiState.isTimePickerVisible
                        ),
                        dateUiState = currentUiState.dateUiState.copy(
                            isDatePickerVisible = if (currentUiState.dateUiState.isDatePickerVisible) false else currentUiState.dateUiState.isDatePickerVisible
                        )
                    )
                }
            }
        }
    }

}