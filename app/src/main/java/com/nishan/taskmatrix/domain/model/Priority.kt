package com.nishan.taskmatrix.domain.model

sealed class Priority(val level: Int, val title: String) {
    object Low    : Priority(1, "Low")
    object Medium : Priority(2, "Medium")
    object High   : Priority(3, "High")

    companion object {
        val values by lazy { listOf(Low, Medium, High) }
    }
}
