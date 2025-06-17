package com.nishan.taskmatrix.util

import androidx.compose.ui.graphics.Color

sealed interface PriorityMatrix {
    val color: Color
    val displayText: String
    val stars: Int
    object Low: PriorityMatrix {
        override val color = Color(0xffd4efdf)
        override val displayText = "Low"
        override val stars = 1
    }
    object Medium: PriorityMatrix {
        override val color = Color(0xfffae5d3 )
        override val displayText = "Medium"
        override val stars = 2
    }
    object High: PriorityMatrix {
        override val color = Color(0xfff5b7b1)
        override val displayText = "High"
        override val stars = 3
    }
}