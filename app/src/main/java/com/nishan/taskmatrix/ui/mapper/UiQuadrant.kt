package com.nishan.taskmatrix.ui.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.nishan.taskmatrix.domain.model.MatrixQuadrant

data class UiQuadrant(
    val title: String,
    val color: Color,
    val icon: ImageVector,
    val taskCount: Int
)

fun MatrixQuadrant.toUi(count: Int): UiQuadrant = when (this) {
    MatrixQuadrant.DoFirst ->
        UiQuadrant("Do First", Color(0xFF4CAF50), Icons.Filled.PriorityHigh, count)

    MatrixQuadrant.Schedule ->
        UiQuadrant("Schedule", Color(0xFF2196F3), Icons.Filled.DateRange, count)

    MatrixQuadrant.Delegate ->
        UiQuadrant("Delegate", Color(0xFFFFA000), Icons.Filled.Group, count)

    MatrixQuadrant.Eliminate ->
        UiQuadrant("Eliminate", Color(0xFFF44336), Icons.Filled.Delete, count)
}