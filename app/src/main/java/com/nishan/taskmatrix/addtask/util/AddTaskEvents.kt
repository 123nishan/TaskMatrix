package com.nishan.taskmatrix.addtask.util

import com.nishan.taskmatrix.domain.model.Priority

sealed class AddTaskEvents {
    object ShowDatePicker: AddTaskEvents()
//    object ShowTimePicker: AddTaskEvents()
    object ShowStartTimePicker: AddTaskEvents()
    object ShowEndTimePicker: AddTaskEvents()
//    object ShowPriorityPicker: AddTaskEvents()
    object SaveTask: AddTaskEvents()
    data class AllDaySelected(val value: Boolean): AddTaskEvents()
    data class PrioritySelected(val priority: Priority): AddTaskEvents()
    object ExpandDateTimeSection: AddTaskEvents()
    object CollapseDateTimeSection: AddTaskEvents()
}