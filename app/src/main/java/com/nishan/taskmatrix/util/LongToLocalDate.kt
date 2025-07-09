package com.nishan.taskmatrix.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun Long.toLocalDate(): LocalDate? =
    this.let{
        Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }