package com.nishan.taskmatrix.domain

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun toEpochMillis(dateMillis: Long, hour: Int, minute: Int): Long {
    val localDate = Instant.ofEpochMilli(dateMillis)
        .atZone(ZoneId.systemDefault()) // Convert from UTC to local timezone
        .toLocalDate()                  // Extract just the date
    val localTime = LocalTime.of(hour, minute)

    return LocalDateTime.of(localDate, localTime)
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()
}
