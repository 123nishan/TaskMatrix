package com.nishan.taskmatrix.domain.model

import com.nishan.taskmatrix.util.Priority

data class Task(
    val title:String,
    val description:String,
    val priority: Priority,
    val date:Long?,
    val time:Long?,
    val isAllDay:Boolean
)