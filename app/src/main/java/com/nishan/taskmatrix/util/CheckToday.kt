package com.nishan.taskmatrix.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun isToday(date: Long?, isAllDay: Boolean): Boolean {
    if (isAllDay) {
        date?.let {
            val dateTime = Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val today = LocalDate.now()
            return@isToday dateTime == today

        }
        return false
    } else {
        return false
    }
}