//package com.nishan.taskmatrix.util
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Group
//import androidx.compose.material.icons.filled.PriorityHigh
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//
//sealed interface MatrixQuadrant {
//    val title: String
//    val color: Color
//    val icon: ImageVector
//    object DoFirst : MatrixQuadrant {
//        override val title = "Do First"
//        override val color = Color(0xFF4CAF50)
//        override val icon  = Icons.Filled.PriorityHigh    // e.g. a “!” or priority icon
//    }
//
//    object Schedule : MatrixQuadrant {
//        override val title = "Schedule"
//        override val color = Color(0xFF2196F3)
//        override val icon  = Icons.Filled.DateRange
//    }
//
//    object Delegate : MatrixQuadrant {
//        override val title = "Delegate"
//        override val color = Color(0xFFFFA000)
//        override val icon  = Icons.Filled.Group
//    }
//
//    object Eliminate : MatrixQuadrant {
//        override val title = "Eliminate"
//        override val color = Color(0xFFF44336)
//        override val icon = Icons.Filled.Delete
//    }
//}

