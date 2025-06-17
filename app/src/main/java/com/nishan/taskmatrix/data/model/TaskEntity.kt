package com.nishan.taskmatrix.data.model

import android.adservices.topics.Topic
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nishan.taskmatrix.domain.model.Task

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "priority") val priority: Int,
    @ColumnInfo(name = "date") val date: Long?,
    @ColumnInfo(name = "time") val time: Long?,
//    @ColumnInfo(name = "start_time") val startTime: Long?,
//    @ColumnInfo(name = "end_time") val endTime: Long?,
    @ColumnInfo(name = "is_all_day") val isAllDay: Boolean
)

fun Task.asEntity() = TaskEntity(
    title = title,
    description = description,
    priority = priority.level,
    date = date,
    time = startTime,
//    startTime = startTime,
//    endTime = endTime,
    isAllDay = isAllDay
)