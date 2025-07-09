package com.nishan.taskmatrix.domain.model


data class Task(
    val title: String,
    val description: String,
    val priority: Priority,
    val date: Long?,
    val startTime: Long?,
    val endTime: Long?,
    val isAllDay: Boolean
)

