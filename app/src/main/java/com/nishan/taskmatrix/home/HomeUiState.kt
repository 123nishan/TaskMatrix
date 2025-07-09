package com.nishan.taskmatrix.home

import com.nishan.taskmatrix.domain.model.MatrixQuadrant
import com.nishan.taskmatrix.domain.model.Task

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data object LoadFailed : HomeUiState

    data class Success(
        val feed: List<Task>,
        val quadrantCount: Map<MatrixQuadrant,Int>
    ): HomeUiState
}

