package com.nishan.taskmatrix.util

import androidx.compose.ui.graphics.Color

sealed interface Priority {
    val level: Int
    val displayText: String
    val color: Color
     object Low: Priority {
         override val level = 1
         override val displayText = "Low"
         override val color = Color.Green
     }
     object Medium: Priority {
         override val level = 2
         override val displayText = "Medium"
         override val color = Color.Yellow
     }
     object High: Priority {
         override val level = 3
         override val displayText = "High"
         override val color = Color.Red
     }

}