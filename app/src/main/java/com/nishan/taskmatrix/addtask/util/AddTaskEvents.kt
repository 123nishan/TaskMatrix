package com.nishan.taskmatrix.addtask.util

sealed class AddTaskEvents {
    object ShowDatePicker: AddTaskEvents()
    object ShowTimePicker: AddTaskEvents()
    object ShowPriorityPicker: AddTaskEvents()
    object SaveTask: AddTaskEvents()
    data class AllDaySelected(val value: Boolean): AddTaskEvents()
    object ExpandDateTimeSection: AddTaskEvents()
    object CollapseDateTimeSection: AddTaskEvents()
}