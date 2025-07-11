package com.nishan.taskmatrix.domain.mapper

import com.nishan.taskmatrix.domain.model.MatrixQuadrant
import java.time.LocalDate

fun mapToQuadrant(
    stars: Int,
    dueDate: LocalDate?,
    allDay: Boolean
): MatrixQuadrant = when {
    stars == 3 && (dueDate!! > LocalDate.now()) -> MatrixQuadrant.Schedule
    stars == 3 -> MatrixQuadrant.DoFirst
    stars == 2 && dueDate == LocalDate.now() -> MatrixQuadrant.Delegate
    else -> MatrixQuadrant.Eliminate
}

fun List<MatrixQuadrant>.toQuadrantCounts(): Map<MatrixQuadrant,Int> {
    val counts = this.groupingBy { it }.eachCount()

    return MatrixQuadrant.values.associateWith {
        counts[it] ?: 0
    }
}

