package com.nishan.taskmatrix.ui.mapper

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.nishan.taskmatrix.domain.model.Priority

@Immutable
data class UiPriority(
    val level: Int,
    val title: String,
    val color: Color
)

// extension to convert
fun Priority.toUi(): UiPriority = when(this) {
    Priority.Low    -> UiPriority(1, "Low",    Color(0xFFd4efdf))
    Priority.Medium -> UiPriority(2, "Medium", Color(0xFFfae5d3))
    Priority.High   -> UiPriority(3, "High",   Color(0xFFf5b7b1))
}