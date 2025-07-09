package com.nishan.taskmatrix.domain.model

sealed class MatrixQuadrant(
    val id:String,
    val title: String
){
    object DoFirst   : MatrixQuadrant("DO_FIRST",   "Do First")
    object Schedule  : MatrixQuadrant("SCHEDULE",   "Schedule")
    object Delegate  : MatrixQuadrant("DELEGATE",   "Delegate")
    object Eliminate : MatrixQuadrant("ELIMINATE",  "Eliminate")
    companion object {
        val values by lazy { listOf(DoFirst, Schedule, Delegate, Eliminate) }
    }
}
