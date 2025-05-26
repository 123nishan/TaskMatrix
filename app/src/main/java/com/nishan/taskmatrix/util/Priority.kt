package com.nishan.taskmatrix.util

import androidx.compose.ui.graphics.Color

sealed interface Priority {
    val level: Int
    val displayText: String
    val color: Color
     object Low: Priority {
         override val level = 1
         override val displayText = "Low"
         override val color = Color(0xffd4efdf)
     }
     object Medium: Priority {
         override val level = 2
         override val displayText = "Medium"
         override val color = Color(0xfffae5d3 )
     }
     object High: Priority {
         override val level = 3
         override val displayText = "High"
         override val color = Color(0xfff5b7b1)
     }

    companion object {
        val values: List<Priority> = listOf(Low, Medium, High)
    }

}